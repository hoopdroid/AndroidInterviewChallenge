package com.excelsior.codechallenge.eventsOverview.ui.model

import com.excelsior.codechallenge.eventsOverview.ui.EventVO

data class EventData(val eventTimeRange: EventTimeRange,
                     val eventsList: List<EventVO>)

