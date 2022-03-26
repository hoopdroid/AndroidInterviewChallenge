package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventScreen.EventItemVO

interface EventDataSource {
    suspend fun getEvents(filterOptions: FilterOptions): EventData
    suspend fun getEvent(id: String): EventItemVO
}