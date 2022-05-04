package com.excelsior.codechallenge.presentation.eventsOverview.ui

import androidx.lifecycle.LiveData
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.base.ui.BaseViewModel

interface EventsOverviewViewModel : BaseViewModel {
    fun observeEvents(): LiveData<EventsOverviewState>
    fun fetchEvents(inputType: EventsInputType? = null)
}