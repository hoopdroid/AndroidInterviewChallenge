package com.excelsior.codechallenge.data.network.gateway

import com.excelsior.codechallenge.data.network.data.EventItemDTO
import com.excelsior.codechallenge.data.network.data.EventsDTO

interface ApiGateway {
    suspend fun getEvents(): List<EventsDTO>
    suspend fun getEvent(id: String): EventItemDTO
}