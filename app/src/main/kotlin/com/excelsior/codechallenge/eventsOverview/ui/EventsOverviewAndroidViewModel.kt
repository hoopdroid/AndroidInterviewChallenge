package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.model.repository.EventDataSource
import com.excelsior.codechallenge.infrastructure.model.repository.FilterOptions
import com.excelsior.codechallenge.infrastructure.model.repository.SortType
import com.excelsior.codechallenge.infrastructure.model.repository.FieldType
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class EventsOverviewAndroidViewModel : EventsOverviewViewModel, BaseAndroidViewModel() {
    private val eventDataSource: EventDataSource by inject()
    private val eventsLiveData = MutableLiveData<EventsOverviewState>()
    private var filterOptions = FilterOptions(fieldType = FieldType.PRICE, sortType = SortType.Descending)

    init {
        fetchEvents(EventsInputType.FIELD)
    }

    override fun fetchEvents(inputType: EventsInputType?) {
        updateFilterOptions(inputType)

        eventsLiveData.postValue(EventsOverviewState.Loading)
        viewModelScope.launch {
            try {
                val events =
                    eventDataSource.getEvents(filterOptions)
                eventsLiveData.postValue(
                    EventsOverviewState.EventsLoaded(
                        events.eventTimeRange,
                        events.eventsList.map { EventMapper().fromSource(it) },
                        filterOptions
                    )
                )
            } catch (e: Exception) {
                eventsLiveData.postValue(EventsOverviewState.Error())
            }
        }
    }

    private fun updateFilterOptions(inputType: EventsInputType?) {
        when (inputType) {
            EventsInputType.SORT -> {
                filterOptions = filterOptions.copy(
                    fieldType = filterOptions.fieldType,
                    sortType = switchSortType(filterOptions.sortType)
                )
            }
            EventsInputType.FIELD -> {
                filterOptions = filterOptions.copy(
                    fieldType = switchFieldType(filterOptions.fieldType),
                    sortType = filterOptions.sortType
                )
            }
        }
    }

    override fun observeEvents(): LiveData<EventsOverviewState> {
        return eventsLiveData
    }

    private fun switchFieldType(fieldType: FieldType): FieldType {
        return if (fieldType == FieldType.DATE) {
            FieldType.PRICE
        } else {
            FieldType.DATE
        }
    }

    private fun switchSortType(sortType: SortType): SortType {
        return if (sortType == SortType.Ascending) {
            SortType.Descending
        } else {
            SortType.Ascending
        }
    }
}
