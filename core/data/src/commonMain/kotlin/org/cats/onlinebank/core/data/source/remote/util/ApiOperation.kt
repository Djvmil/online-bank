package org.cats.onlinebank.core.data.source.remote.util

import kotlinx.serialization.Serializable

@Serializable
sealed interface ApiOperation<T> {
  data class SuccessCall<T>(val data: T) : ApiOperation<T>

  data class FailureCall<T>(val exception: Exception) : ApiOperation<T>
}

inline fun <T> ApiOperation<T>.onFailure(block: (Exception) -> Unit): ApiOperation<T> {
  if (this is ApiOperation.FailureCall) block(exception)
  return this
}

inline fun <T> ApiOperation<T>.onSuccess(block: (T) -> Unit): ApiOperation<T> {
  if (this is ApiOperation.SuccessCall) block(data)
  return this
}

inline fun <T> safeApiCall(apiCall: () -> T): ApiOperation<T> {
  return try {
    ApiOperation.SuccessCall(data = apiCall())
  } catch (e: Exception) {
    ApiOperation.FailureCall(exception = e)
  }
}
