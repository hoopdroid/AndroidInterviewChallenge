package com.excelsior.codechallenge.eventsOverview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.infrastructure.domain.EventListInteractor
import com.excelsior.codechallenge.infrastructure.model.repository.FilterOptions
import com.excelsior.codechallenge.infrastructure.model.repository.SortType
import com.excelsior.codechallenge.infrastructure.model.repository.FieldType
import com.excelsior.codechallenge.infrastructure.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch

class EventsOverviewAndroidViewModel(private val interactor: EventListInteractor) :
    EventsOverviewViewModel, BaseAndroidViewModel() {
    private val eventsLiveData = MutableLiveData<EventsOverviewState>()
    private var filterOptions = FilterOptions(
        fieldType = FieldType.PRICE,
        sortType = SortType.Descending
    )

    init {
        fetchEvents(EventsInputType.FIELD)
    }

    override fun fetchEvents(inputType: EventsInputType?) {
        inputType?.let {
            updateFilterOptions(inputType)
        }

        eventsLiveData.postValue(EventsOverviewState.Loading)
        viewModelScope.launch {
            try {
                val events = interactor.loadEvents(filterOptions)
                eventsLiveData.postValue(
                    EventsOverviewState.EventsLoaded(
                        events.eventTimeRange,
                        events.eventsList,
                        filterOptions
                    )
                )
            } catch (e: Exception) {
                eventsLiveData.postValue(EventsOverviewState.Error())
            }
        }
    }

    private fun updateFilterOptions(inputType: EventsInputType) {
        filterOptions = when (inputType) {
            EventsInputType.SORT -> {
                filterOptions.copy(
                    fieldType = filterOptions.fieldType,
                    sortType = switchSortType(filterOptions.sortType)
                )
            }
            EventsInputType.FIELD -> {
                filterOptions.copy(
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
