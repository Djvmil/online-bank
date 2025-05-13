package org.cats.onlinebank.core.common.utils

import kotlinx.serialization.json.Json
import kotlin.math.pow
import kotlin.math.round

object Utils {
    fun encode(value: String): String {
        return Json.encodeToString(value)
    }

    fun decode(value: String): String {
        return Json.decodeFromString(value)
    }

    fun Int.zeroPrefixed(
        maxLength: Int,
    ): String {
        if (this < 0 || maxLength < 1) return ""

        val string = this.toString()
        val currentStringLength = string.length
        return if (maxLength <= currentStringLength) {
            string
        } else {
            val diff = maxLength - currentStringLength
            var prefixedZeros = ""
            repeat(diff) {
                prefixedZeros += "0"
            }
            "$prefixedZeros$string"
        }
    }

    fun Double.roundTo(dec: Int, round: Boolean = false): Double {
        val factor = 10.0.pow(dec)
        val scaledValue = this * factor
        return (if (round) round(scaledValue) else scaledValue.toInt().toDouble()) / factor
    }
}