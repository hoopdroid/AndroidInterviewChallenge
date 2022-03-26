package com.excelsior.codechallenge.infrastructure.model.repository

interface EventDataSource {
    suspend fun getEvents(filterOptions: FilterOptions): EventData
}