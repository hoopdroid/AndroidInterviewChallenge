package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class EventsOverviewAndroidViewModel : BaseAndroidViewModel(), EventsOverviewViewModel {
    private val service: ApiGateway by inject()
    private val eventsLiveData = MutableLiveData<List<EventVO>>()

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            try {
                val events = service.getEvents()
                eventsLiveData.postValue(events)
            } catch (e: Exception) {
                eventsLiveData.postValue(emptyList())
            }
        }
    }

    override fun observeEvents(): LiveData<List<EventVO>> {
        return eventsLiveData
    }

}
