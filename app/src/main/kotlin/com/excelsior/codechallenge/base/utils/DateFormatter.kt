package com.excelsior.codechallenge.base.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object DateFormatter {
    private const val DATE_FORMAT = "dd MMMM yyyy"

    private val dateMonthFormat by lazy {
        DateTimeFormat.forPattern(DATE_FORMAT)
    }

    fun print(dateTime: DateTime): String {
        return dateMonthFormat.print(dateTime)
    }
}