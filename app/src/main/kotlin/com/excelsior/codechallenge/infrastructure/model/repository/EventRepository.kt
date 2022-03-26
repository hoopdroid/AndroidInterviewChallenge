package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.eventsOverview.ui.EventVO

interface EventRepository {
    suspend fun getEvents(filterOptions: FilterOptions): List<EventVO>
}