package com.excelsior.codechallenge.infrastructure.domain

import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.model.repository.*
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter
import org.joda.time.DateTime
import java.lang.Exception

class EventsListUseCaseImpl(
    private val dataSource: EventDataSource,
    private val eventMapper: EventMapper
) : EventsListUseCase {
    override suspend fun invoke(filterOptions: FilterOptions): EventData {
        val eventsList = dataSource.getEvents(filterOptions)
        val timeRange = resolveEventsTimeRange(eventsList)
        return EventData(timeRange, eventsList.map { eventMapper.fromSource(it) })
    }

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