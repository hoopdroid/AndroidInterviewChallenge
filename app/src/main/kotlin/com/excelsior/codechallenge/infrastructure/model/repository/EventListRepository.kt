package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.network.data.EventDTO
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway

class EventListRepository(private val apiGateway: ApiGateway) : EventRepository {
    override suspend fun getEvents(filterOptions: FilterOptions): List<EventVO> {
        return apiGateway.getEvents()
            .applyFilter(filterOptions)
    }

    private fun List<EventVO>.applyFilter(filterOptions: FilterOptions): List<EventVO> {
        return when (filterOptions.sortType) {
            SortType.Ascending(SortType.Type.ticket_price) -> sortedBy { it.price }
            SortType.Descending(SortType.Type.ticket_price) -> sortedByDescending { it.price }
            SortType.Ascending(SortType.Type.date) -> sortedBy { it.date }
            SortType.Descending(SortType.Type.date) -> sortedByDescending { it.date }
            else -> {
                this
            }
        }
    }
}