package com.excelsior.codechallenge.infrastructure.model

import java.util.Date

data class Event(
    val id: String,
    val name: String,
    val formattedDate: String,
    val date: Date?,
)