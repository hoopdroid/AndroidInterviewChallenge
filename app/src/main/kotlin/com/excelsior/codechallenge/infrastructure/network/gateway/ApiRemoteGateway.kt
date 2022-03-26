package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.network.ApiService

class ApiRemoteGateway(private val service: ApiService, private val eventMapper: EventMapper) :
    ApiGateway {

    override suspend fun getEvents(): List<EventVO> {
        return service.get().getEvents().map { eventMapper.fromSource(it) }
    }
}