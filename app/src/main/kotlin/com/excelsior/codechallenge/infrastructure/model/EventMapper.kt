package com.excelsior.codechallenge.infrastructure.model

import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.network.data.EventDTO

class EventMapper {

    fun fromSource(event: EventDTO): EventVO =
        EventVO(
            id = event.guid,
            name = event.event,
            price = event.ticketPrice,
            formattedDate = event.date,
            date = event.date
        )
}