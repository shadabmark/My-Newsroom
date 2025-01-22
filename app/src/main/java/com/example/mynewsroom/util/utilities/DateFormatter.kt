package com.example.mynewsroom.util.utilities

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun dateFormatter(inputDataTime: String?): String {
    val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val outputFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.LONG)
        .withLocale(Locale.getDefault())

    val dateString = try {
        val dateTime = OffsetDateTime.parse(inputDataTime, inputFormatter)
        dateTime.format(outputFormatter)
    } catch (e: Exception) {
        ""
    }
    return dateString
}
