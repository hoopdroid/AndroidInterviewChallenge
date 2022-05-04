package com.excelsior.codechallenge.domain

import com.excelsior.codechallenge.presentation.eventScreen.ui.EventItemVO
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventData
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.FilterOptions

interface EventsInteractor {
    suspend fun loadEvents(filterOptions: FilterOptions): EventData
    suspend fun loadEvent(id: String): EventItemVO
}