package org.cats.onlinebank.core.common.model

data class OBError(val message: String = "", val code: Int = 0, val throwable: Throwable? = null) {
  fun isMessage(): Boolean = message.isNotEmpty()

  fun isThrowable(): Boolean = throwable != null
}
