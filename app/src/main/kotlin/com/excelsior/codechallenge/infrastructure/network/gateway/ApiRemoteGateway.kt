package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.infrastructure.network.ApiService
import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO

class ApiRemoteGateway(private val service: ApiService) :
    ApiGateway {

    override suspend fun getEvents(): List<EventsDTO> = service.get().getEvents()
    override suspend fun getEvent(id: String): EventItemDTO = service.get().getEvent(id)
}