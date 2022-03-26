package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.LiveData
import com.excelsior.codechallenge.infrastructure.ui.BaseViewModel

interface EventsOverviewViewModel : BaseViewModel {
    fun observeEvents(): LiveData<List<EventVO>>
}