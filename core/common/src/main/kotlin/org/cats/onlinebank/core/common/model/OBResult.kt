package org.cats.onlinebank.core.common.model

/*
V = Value
E = Error
*/
sealed class OBResult<out V, out E> {

  data object Loading : OBResult<Nothing, Nothing>()

  data class Success<out V : Any?>(val value: V) : OBResult<V, Nothing>()

  data class Failure<out E : Any?>(val error: E) : OBResult<Nothing, E>()
}

inline fun <V, U, reified E : Any?> OBResult<V, E>.mapOB(transform: (V) -> U): OBResult<U, E> {
  return when (this) {
    OBResult.Loading -> OBResult.Loading
    is OBResult.Success -> OBResult.Success(transform(value))
    is OBResult.Failure -> OBResult.Failure(error)
  }
}
