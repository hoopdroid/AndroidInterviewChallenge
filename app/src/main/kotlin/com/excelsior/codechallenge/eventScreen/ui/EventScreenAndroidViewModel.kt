package com.excelsior.codechallenge.eventScreen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.infrastructure.domain.EventsInteractor
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch

class EventScreenAndroidViewModel(private val interactor: EventsInteractor) : EventScreenViewModel, BaseAndroidViewModel() {
    private val eventsLiveData = MutableLiveData<EventScreenState>()

    override fun getEvent(id: String) {
        eventsLiveData.postValue(EventScreenState.Loading)

        viewModelScope.launch {
            try {
                eventsLiveData.postValue(EventScreenState.EventsLoaded(interactor.loadEvent(id)))
            } catch (e: Exception) {
                eventsLiveData.postValue(EventScreenState.Error())
            }
        }
    }

    override fun observeEvent(): LiveData<EventScreenState> {
        return eventsLiveData
    }
}
