package com.excelsior.codechallenge.infrastructure.network.gateway

class ApiRemoteGateway : ApiGateway {

    override suspend fun getEvents(): List<Map<String, Any?>> =
        emptyList()
}