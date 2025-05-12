package org.cats.onlinebank.core.common.utils

import java.util.Locale

actual fun formatString(
    format: String,
    args: List<Any>
): String {return String.format(Locale.getDefault(), format, *args.toTypedArray())
}