package org.cats.onlinebank.core.common

expect fun formatString(
    format: String,
    args: List<Any>
): String