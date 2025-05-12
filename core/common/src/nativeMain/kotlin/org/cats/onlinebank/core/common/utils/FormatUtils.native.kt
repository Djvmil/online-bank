package org.cats.onlinebank.core.common.utils

import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

actual fun formatString(format: String, args: List<Any>): String {
   // val argStrings = args.map { it?.toString() ?: "null" }.toTypedArray()
    return format
}