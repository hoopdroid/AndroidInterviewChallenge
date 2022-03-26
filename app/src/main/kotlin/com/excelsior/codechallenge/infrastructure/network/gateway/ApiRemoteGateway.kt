package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.infrastructure.network.ApiService
import com.excelsior.codechallenge.infrastructure.network.data.EventDTO

class ApiRemoteGateway(private val service: ApiService) :
    ApiGateway {

    override suspend fun getEvents(): List<EventDTO> {
        return service.get().getEvents()
    }
}