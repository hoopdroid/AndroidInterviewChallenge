package com.excelsior.codechallenge.eventsOverview.ui

import java.util.Date

data class EventVO(
    val id: String,
    val name: String,
    val formattedDate: String,
    val date: Date?,
)