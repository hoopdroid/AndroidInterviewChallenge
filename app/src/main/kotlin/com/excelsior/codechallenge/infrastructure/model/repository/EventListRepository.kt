package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter
import org.joda.time.DateTime
import java.text.ParseException

class EventListRepository(private val apiGateway: ApiGateway) : EventRepository {

    override suspend fun getEvents(filterOptions: FilterOptions): EventData {
        val eventsList = apiGateway.getEvents()

        return EventData(
            resolveEventsTimeRange(eventsList),
            eventsList.applyFilter(filterOptions)
        )
    }

    private fun List<EventVO>.applyFilter(filterOptions: FilterOptions): List<EventVO> {
        return when (filterOptions.sortType) {
            SortType.Ascending(SortType.Type.ticket_price) -> sortedBy { it.price }
            SortType.Descending(SortType.Type.ticket_price) -> sortedByDescending { it.price }
            SortType.Ascending(SortType.Type.date) -> sortedBy { it.formattedDate }
            SortType.Descending(SortType.Type.date) -> sortedByDescending { it.formattedDate }
            else -> {
                this
            }
        }
    }

    private fun resolveEventsTimeRange(eventsList: List<EventVO>): EventTimeRange {
        val sortedByDateEvents = eventsList.sortedBy { it.formattedDate }

        return try {
            val firstDate = sortedByDateEvents.first().formattedDate
            val lastDate = sortedByDateEvents.last().formattedDate
            EventTimeRange(firstDate, lastDate)
        } catch (parseException: ParseException) {
            val exceptionDate = DateFormatter.print(DateTime.now())
            EventTimeRange(exceptionDate, exceptionDate)
        }
    }

}