package com.excelsior.codechallenge.data.repository

import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.FilterOptions
import com.excelsior.codechallenge.data.network.data.EventItemDTO
import com.excelsior.codechallenge.data.network.data.EventsDTO

interface EventDataSource {
    suspend fun getEvents(filterOptions: FilterOptions): List<EventsDTO>
    suspend fun getEvent(id: String): EventItemDTO
}