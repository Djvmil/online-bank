package org.cats.onlinebank.core.common.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.cats.onlinebank.core.common.utils.Utils.zeroPrefixed

object DateTimeKtx {

    fun getFormattedDate(
        timestamp: String,
    ): String {
        val localDateTime = timestampToLocalDateTime(timestamp)
        val date = localDateTime.date
        val day = date.dayOfMonth
        val month = date.monthNumber
        val year = date.year

        return "${day.zeroPrefixed(2)}/${month.zeroPrefixed(2)}/${year}"
    }

    private fun timestampToLocalDateTime(timestamp: String): LocalDateTime {
        return Instant.fromEpochSeconds(timestamp.toLong()).toLocalDateTime(TimeZone.currentSystemDefault())
    }
}