package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.LiveData
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.infrastructure.model.repository.SortType
import com.excelsior.codechallenge.infrastructure.network.data.EventDTO
import com.excelsior.codechallenge.infrastructure.ui.BaseViewModel

interface EventsOverviewViewModel : BaseViewModel {
    fun observeEvents(): LiveData<EventsOverviewState>
    fun fetchEvents(inputType: EventsInputType? = null)
}