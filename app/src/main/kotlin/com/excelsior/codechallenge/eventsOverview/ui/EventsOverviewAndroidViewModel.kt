package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.infrastructure.model.repository.EventRepository
import com.excelsior.codechallenge.infrastructure.model.repository.FilterOptions
import com.excelsior.codechallenge.infrastructure.model.repository.SortType
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class EventsOverviewAndroidViewModel : EventsOverviewViewModel, BaseAndroidViewModel() {
    private val eventRepository: EventRepository by inject()
    private val eventsLiveData = MutableLiveData<EventsOverviewState>()

    private var byFieldSort = SortType.Type.ticket_price
    private var currentSortType: SortType = SortType.Ascending(byFieldSort)

    init {
        fetchEvents(EventsInputType.FIELD)
    }

    override fun fetchEvents(inputType: EventsInputType?) {
        when (inputType) {
            EventsInputType.SORT -> switchSortType()
            EventsInputType.FIELD -> updateByField()
        }

        eventsLiveData.postValue(EventsOverviewState.Loading)
        viewModelScope.launch {
            try {
                val events =
                    eventRepository.getEvents(FilterOptions(sortType = currentSortType))
                eventsLiveData.postValue(
                    EventsOverviewState.EventsLoaded(
                        events,
                        currentSortType
                    )
                )
            } catch (e: Exception) {
                eventsLiveData.postValue(EventsOverviewState.Error())
            }
        }
    }

    override fun observeEvents(): LiveData<EventsOverviewState> {
        return eventsLiveData
    }

    private fun updateByField() {
        byFieldSort = if (byFieldSort == SortType.Type.date) {
            SortType.Type.ticket_price
        } else {
            SortType.Type.date
        }

        currentSortType = when(currentSortType) {
            is SortType.Ascending -> SortType.Ascending(byFieldSort)
            is SortType.Descending -> SortType.Descending(byFieldSort)
        }
    }

    private fun switchSortType() {
        currentSortType = if (currentSortType == SortType.Ascending(byFieldSort)) {
            SortType.Descending(byFieldSort)
        } else {
            SortType.Ascending(byFieldSort)
        }
    }

}
