package com.excelsior.codechallenge.data.network.data

import org.joda.time.DateTime

data class EventItemDTO(
    val guid: String,
    val event: String,
    val ticketPrice: Double,
    val date: DateTime,
    val description: String,
    val phone: String,
    val address: String
)

