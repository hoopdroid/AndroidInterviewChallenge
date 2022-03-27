package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventsOverview.ui.EventVO

data class EventData(val eventTimeRange: EventTimeRange,
                     val eventsList: List<EventVO>)

data class EventTimeRange(val fromDate: String,
                          val untilDate: String)