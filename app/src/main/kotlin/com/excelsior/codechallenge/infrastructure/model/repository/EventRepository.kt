package com.excelsior.codechallenge.infrastructure.model.repository

interface EventRepository {
    suspend fun getEvents(filterOptions: FilterOptions): EventData
}