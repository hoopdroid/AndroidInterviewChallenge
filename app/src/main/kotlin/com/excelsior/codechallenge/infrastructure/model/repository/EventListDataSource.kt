package com.excelsior.codechallenge.infrastructure.model.repository

import android.media.metrics.Event
import com.excelsior.codechallenge.eventScreen.EventItemVO
import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter
import org.joda.time.DateTime
import java.lang.Exception
import java.text.ParseException

class EventListDataSource(
    private val apiGateway: ApiGateway,
    private val eventMapper: EventMapper
) : EventDataSource {

    override suspend fun getEvents(filterOptions: FilterOptions): EventData {
        val eventsList = apiGateway.getEvents()
        return EventData(
            resolveEventsTimeRange(eventsList),
            eventsList
                .applyFilter(filterOptions)
        )
    }

    override suspend fun getEvent(id: String): EventItemVO {
        return eventMapper.fromItem(apiGateway.getEvent(id))
    }

    private fun List<EventsDTO>.applyFilter(filterOptions: FilterOptions): List<EventsDTO> {
        return when (filterOptions.sortType) {
            SortType.Ascending -> sortedByOptions(filterOptions)
            SortType.Descending -> sortedByOptions(filterOptions).asReversed()
        }
    }

    private fun List<EventsDTO>.sortedByOptions(
        filterOptions: FilterOptions
    ) = if (filterOptions.fieldType == FieldType.PRICE) {
        sortedBy { it.ticketPrice }
    } else {
        sortedBy { it.date }
    }

    private fun resolveEventsTimeRange(eventsList: List<EventsDTO>): EventTimeRange {
        val sortedByDateEvents = eventsList.sortedBy { it.date }

        return try {
            val firstDate = DateFormatter.print(sortedByDateEvents.first().date)
            val lastDate = DateFormatter.print(sortedByDateEvents.last().date)
            EventTimeRange(firstDate, lastDate)
        } catch (parseException: Exception) {
            val exceptionDate = DateFormatter.print(DateTime.now())
            EventTimeRange(exceptionDate, exceptionDate)
        }
    }

}