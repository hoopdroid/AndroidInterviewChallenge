package com.excelsior.codechallenge.presentation.eventsOverview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.domain.EventsInteractor
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.FilterOptions
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.SortType
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.FieldType
import com.excelsior.codechallenge.base.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch

class EventsOverviewAndroidViewModel(private val interactor: EventsInteractor) :
    EventsOverviewViewModel, BaseAndroidViewModel() {
    private val eventsLiveData = MutableLiveData<EventsOverviewState>()
    private var filterOptions = FilterOptions()

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
            } catch (exception: Exception) {
                eventsLiveData.postValue(EventsOverviewState.Error)
            }
        }
    }

    private fun updateFilterOptions(inputType: EventsInputType) {
        filterOptions = when (inputType) {
            is EventsInputType.SortFilter -> {
                filterOptions.copy(
                    fieldType = filterOptions.fieldType,
                    sortType = switchSortType(filterOptions.sortType)
                )
            }
            is EventsInputType.FieldFilter -> {
                filterOptions.copy(
                    fieldType = switchFieldType(filterOptions.fieldType),
                    sortType = filterOptions.sortType
                )
            }
            is EventsInputType.ShowOutdatedFilter -> {
                filterOptions.copy(
                    fieldType = filterOptions.fieldType,
                    sortType = filterOptions.sortType,
                    needToShowOutDated = filterOptions.needToShowOutDated?.not()
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
