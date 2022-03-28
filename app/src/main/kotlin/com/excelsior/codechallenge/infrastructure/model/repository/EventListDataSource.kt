package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventsOverview.ui.model.FieldType
import com.excelsior.codechallenge.eventsOverview.ui.model.FilterOptions
import com.excelsior.codechallenge.eventsOverview.ui.model.SortType
import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway

class EventListDataSource(
    private val apiGateway: ApiGateway
) : EventDataSource {

    override suspend fun getEvents(filterOptions: FilterOptions): List<EventsDTO> =
        apiGateway.getEvents().applyFilter(filterOptions)

    override suspend fun getEvent(id: String): EventItemDTO {
        return apiGateway.getEvent(id)
    }

    private fun List<EventsDTO>.applyFilter(filterOptions: FilterOptions): List<EventsDTO> {
        val list = when (filterOptions.sortType) {
            SortType.Ascending -> sortedByOptions(filterOptions)
            SortType.Descending -> sortedByOptions(filterOptions).asReversed()
        }
        return list.filter { if (filterOptions.needToShowOutDated == true) true else it.date.isAfterNow }
    }

    private fun List<EventsDTO>.sortedByOptions(
        filterOptions: FilterOptions
    ) = if (filterOptions.fieldType == FieldType.PRICE) {
        sortedBy { it.ticketPrice }
    } else {
        sortedBy { it.date }
    }
}