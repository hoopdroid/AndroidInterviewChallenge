package com.excelsior.codechallenge.domain

import com.excelsior.codechallenge.data.repository.EventDataSource
import com.excelsior.codechallenge.presentation.eventScreen.ui.EventItemVO
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventData
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventTimeRange
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.FilterOptions
import com.excelsior.codechallenge.domain.mapper.EventMapper
import com.excelsior.codechallenge.data.network.data.EventsDTO
import com.excelsior.codechallenge.base.utils.DateFormatter
import java.lang.Exception

class EventsListInteractor(
    private val dataSource: EventDataSource,
    private val eventMapper: EventMapper
) : EventsInteractor {
    override suspend fun loadEvents(filterOptions: FilterOptions): EventData {
        val eventsList = dataSource.getEvents(filterOptions)
        val timeRange = resolveEventsTimeRange(eventsList)
        return EventData(timeRange, eventsList.map { eventMapper.toVO(it) })
    }

    override suspend fun loadEvent(id: String): EventItemVO =
        eventMapper.toVO(dataSource.getEvent(id))

    private fun resolveEventsTimeRange(eventsList: List<EventsDTO>): EventTimeRange {
        val sortedByDateEvents = eventsList.sortedBy { it.date }

        return try {
            val firstDate = DateFormatter.print(sortedByDateEvents.first().date)
            val lastDate = DateFormatter.print(sortedByDateEvents.last().date)
            EventTimeRange(firstDate, lastDate)
        } catch (parseException: Exception) {
            throw parseException
        }
    }
}