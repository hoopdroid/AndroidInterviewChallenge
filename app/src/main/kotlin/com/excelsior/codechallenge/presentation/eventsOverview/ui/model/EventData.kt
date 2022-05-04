package com.excelsior.codechallenge.presentation.eventsOverview.ui.model

import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventVO

data class EventData(val eventTimeRange: EventTimeRange,
                     val eventsList: List<EventVO>)

