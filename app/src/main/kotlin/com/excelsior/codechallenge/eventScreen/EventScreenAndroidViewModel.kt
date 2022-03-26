package com.excelsior.codechallenge.eventScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.infrastructure.model.repository.EventDataSource
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class EventsScreenAndroidViewModel : EventsScreenViewModel, BaseAndroidViewModel() {
    private val eventDataSource: EventDataSource by inject()
    private val eventsLiveData = MutableLiveData<EventItemVO>()

    override fun getEvent(id: String) {
        viewModelScope.launch {
            try {
                val event =
                    eventDataSource.getEvent(id)
                eventsLiveData.postValue(
                    event
                )
            } catch (e: Exception) {

            }
        }
    }

    override fun observeEvent(): LiveData<EventItemVO> {
        return eventsLiveData
    }
}
