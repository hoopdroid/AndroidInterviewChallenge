package com.excelsior.codechallenge.infrastructure.network

import retrofit2.http.GET

interface Api {

    @GET("events")
    suspend fun getEvents(): List<Map<String, Any?>>
}