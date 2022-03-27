package com.excelsior.codechallenge.infrastructure.model

import com.excelsior.codechallenge.eventScreen.EventItemVO
import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter

class EventMapper {

    fun toVO(event: EventsDTO): EventVO =
        EventVO(
            id = event.guid,
            name = event.event,
            price = event.ticketPrice,
            formattedDate = DateFormatter.print(event.date)
        )

    fun toVO(event: EventItemDTO): EventItemVO =
        EventItemVO(
            id = event.guid,
            name = event.event,
            price = event.ticketPrice,
            formattedDate = DateFormatter.print(event.date),
            description = event.description,
            address = event.address,
            phone = event.phone
        )
}