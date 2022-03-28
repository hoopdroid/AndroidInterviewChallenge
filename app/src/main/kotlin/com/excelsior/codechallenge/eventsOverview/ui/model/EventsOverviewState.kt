package com.excelsior.codechallenge.eventsOverview.ui.model

import com.excelsior.codechallenge.eventsOverview.ui.EventVO

sealed class EventsOverviewState {
    object Loading : EventsOverviewState()
    object Error : EventsOverviewState()
    data class EventsLoaded(
        val eventsTimeRange: EventTimeRange, val eventsList: List<EventVO>,
        val filterOptions: FilterOptions
    ) : EventsOverviewState()
}
