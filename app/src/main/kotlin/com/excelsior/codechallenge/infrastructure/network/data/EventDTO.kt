package com.excelsior.codechallenge.infrastructure.network.data

data class EventDTO(
    val guid: String,
    val event: String,
    val ticketPrice: Double,
    val date: String
) {
    companion object {
        const val TICKET_PRICE_FIELD = "ticketPrice"
        const val DATE_FIELD = "date"
    }

}

