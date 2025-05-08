package org.cats.onlinebank.core.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DateUtil {
    fun convertTimestampToDate(timestamp: Long): LocalDate {
        val instant = Instant.fromEpochSeconds(timestamp)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun convertTimestampToLocalDateTime(timestamp: Long): LocalDateTime {
        val instant = Instant.fromEpochSeconds(timestamp)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun convertDateToTimestamp(date: LocalDateTime): Long {
        val instant = date.toInstant(TimeZone.currentSystemDefault())
        return instant.epochSeconds
    }

    fun getCurrentDate(): LocalDateTime {
        val now = Clock.System.now()
        return now.toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun formatDate(date: LocalDateTime, pattern: String): String {
        return date.toString()
    }
}