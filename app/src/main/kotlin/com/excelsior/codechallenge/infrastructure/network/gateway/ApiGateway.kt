package com.excelsior.codechallenge.infrastructure.network.gateway

interface ApiGateway {

    suspend fun getEvents(): List<Map<String, Any?>>
}