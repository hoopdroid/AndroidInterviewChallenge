package com.excelsior.codechallenge.data.network.gateway

import com.excelsior.codechallenge.data.network.Api
import com.excelsior.codechallenge.data.network.data.EventItemDTO
import com.excelsior.codechallenge.data.network.data.EventsDTO

class ApiRemoteGateway(private val service: Api) :
    ApiGateway {

    override suspend fun getEvents(): List<EventsDTO> = service.getEvents()
    override suspend fun getEvent(id: String): EventItemDTO = service.getEvent(id)
}