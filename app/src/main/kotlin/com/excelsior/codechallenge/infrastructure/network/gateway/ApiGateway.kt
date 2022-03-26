package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.infrastructure.network.data.EventDTO

interface ApiGateway {

    suspend fun getEvents(): List<EventDTO>

    suspend fun getEvent(id: String): EventDTO
}