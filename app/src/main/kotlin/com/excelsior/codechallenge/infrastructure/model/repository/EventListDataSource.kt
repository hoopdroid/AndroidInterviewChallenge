package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventScreen.EventItemVO
import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway

class EventListDataSource(
    private val apiGateway: ApiGateway,
    private val eventMapper: EventMapper
) : EventDataSource {

    override suspend fun getEvents(filterOptions: FilterOptions): List<EventsDTO> =
        apiGateway.getEvents().applyFilter(filterOptions)

    override suspend fun getEvent(id: String): EventItemVO {
        return eventMapper.toVO(apiGateway.getEvent(id))
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
}