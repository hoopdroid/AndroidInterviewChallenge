package com.excelsior.codechallenge.infrastructure.domain

import com.excelsior.codechallenge.eventScreen.ui.EventItemVO
import com.excelsior.codechallenge.eventsOverview.ui.model.EventData
import com.excelsior.codechallenge.eventsOverview.ui.model.EventTimeRange
import com.excelsior.codechallenge.eventsOverview.ui.model.FilterOptions
import com.excelsior.codechallenge.infrastructure.mapper.EventMapper
import com.excelsior.codechallenge.infrastructure.model.repository.*
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter
import java.lang.Exception

class EventListInteractorImpl(
    private val dataSource: EventDataSource,
    private val eventMapper: EventMapper
) : EventListInteractor {
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