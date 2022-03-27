package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventScreen.EventItemVO
import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO

interface EventDataSource {
    suspend fun getEvents(filterOptions: FilterOptions): List<EventsDTO>
    suspend fun getEvent(id: String): EventItemDTO
}