package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.infrastructure.network.Api
import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO

class ApiRemoteGateway(private val service: Api) :
    ApiGateway {

    override suspend fun getEvents(): List<EventsDTO> = service.getEvents()
    override suspend fun getEvent(id: String): EventItemDTO = service.getEvent(id)
}