package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO

interface ApiGateway {
    suspend fun getEvents(): List<EventsDTO>
    suspend fun getEvent(id: String): EventItemDTO
}