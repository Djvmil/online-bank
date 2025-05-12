package org.cats.onlinebank.core.common.utils

import kotlinx.serialization.json.Json

object Utils {
    fun encode(value: String): String {
        return Json.encodeToString(value)
    }

    fun decode(value: String): String {
        return Json.decodeFromString(value)
    }
}