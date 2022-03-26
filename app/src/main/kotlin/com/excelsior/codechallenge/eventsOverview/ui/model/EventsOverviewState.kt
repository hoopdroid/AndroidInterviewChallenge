package com.excelsior.codechallenge.eventsOverview.ui.model

import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.model.repository.SortType

sealed class EventsOverviewState {
    object Loading : EventsOverviewState()
    data class Error(val message: String? = null) : EventsOverviewState()
    data class EventsLoaded(val eventsList: List<EventVO>, val sortType: SortType) : EventsOverviewState()
}
