package com.excelsior.codechallenge.data.network.data

import org.joda.time.DateTime

data class EventsDTO(
    val guid: String,
    val event: String,
    val ticketPrice: Double,
    val date: DateTime
)

