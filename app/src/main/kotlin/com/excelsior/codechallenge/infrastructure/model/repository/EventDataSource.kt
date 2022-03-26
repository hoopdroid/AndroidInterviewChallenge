package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.infrastructure.network.data.EventDTO

interface EventDataSource {
    suspend fun getEvents(filterOptions: FilterOptions): EventData
    // todo remove dto to vo
    suspend fun getEvent(id: String): EventDTO
}