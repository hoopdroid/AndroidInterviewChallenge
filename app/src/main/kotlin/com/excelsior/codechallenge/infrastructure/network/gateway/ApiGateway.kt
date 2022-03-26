package com.excelsior.codechallenge.infrastructure.network.gateway

import com.excelsior.codechallenge.eventsOverview.ui.EventVO

interface ApiGateway {

    suspend fun getEvents(): List<EventVO>
}