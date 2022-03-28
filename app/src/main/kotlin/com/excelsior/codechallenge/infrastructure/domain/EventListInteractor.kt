package com.excelsior.codechallenge.infrastructure.domain

import com.excelsior.codechallenge.eventScreen.ui.EventItemVO
import com.excelsior.codechallenge.eventsOverview.ui.model.EventData
import com.excelsior.codechallenge.eventsOverview.ui.model.FilterOptions

interface EventListInteractor {
    suspend fun loadEvents(filterOptions: FilterOptions): EventData
    suspend fun loadEvent(id: String): EventItemVO
}