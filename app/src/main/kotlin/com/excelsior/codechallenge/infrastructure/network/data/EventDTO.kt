package com.excelsior.codechallenge.infrastructure.network.data

import org.joda.time.DateTime

data class EventDTO(
    val guid: String,
    val event: String,
    val ticketPrice: Double,
    val date: DateTime
) {
    companion object {
        const val TICKET_PRICE_FIELD = "ticketPrice"
        const val DATE_FIELD = "date"
    }

}

