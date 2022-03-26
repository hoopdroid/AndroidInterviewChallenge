package com.excelsior.codechallenge.infrastructure.network.data

import org.joda.time.DateTime

data class EventDTO(
    val guid: String,
    val event: String,
    val ticketPrice: Double,
    val date: DateTime,
    val description: String? = null,
    val phone: String? = null,
    val address: String? = null
)

