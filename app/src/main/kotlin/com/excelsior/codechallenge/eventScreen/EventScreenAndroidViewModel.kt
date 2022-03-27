package com.excelsior.codechallenge.eventScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.infrastructure.domain.EventListInteractor
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch

class EventsScreenAndroidViewModel(private val interactor: EventListInteractor) : EventsScreenViewModel, BaseAndroidViewModel() {
    private val eventsLiveData = MutableLiveData<EventItemVO>()

    override fun getEvent(id: String) {
        viewModelScope.launch {
            try {
                eventsLiveData.postValue(interactor.loadEvent(id))
            } catch (e: Exception) {

            }
        }
    }

    override fun observeEvent(): LiveData<EventItemVO> {
        return eventsLiveData
    }
}
