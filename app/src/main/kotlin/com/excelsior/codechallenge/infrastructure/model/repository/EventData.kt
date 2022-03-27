package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO

data class EventData(val eventTimeRange: EventTimeRange,
                     val eventsList: List<EventsDTO>)

data class EventTimeRange(val fromDate: String,
                          val untilDate: String)