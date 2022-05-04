package com.excelsior.codechallenge.domain.mapper

import com.excelsior.codechallenge.presentation.eventScreen.ui.EventItemVO
import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.data.network.data.EventItemDTO
import com.excelsior.codechallenge.data.network.data.EventsDTO
import com.excelsior.codechallenge.base.utils.DateFormatter

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