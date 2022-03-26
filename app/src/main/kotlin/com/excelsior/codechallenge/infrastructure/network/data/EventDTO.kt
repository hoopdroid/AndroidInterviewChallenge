package com.excelsior.codechallenge.infrastructure.network.data

data class EventDTO(
    val guid: String,
    val event: String,
    val ticketPrice: Double,
    val date: String
)
