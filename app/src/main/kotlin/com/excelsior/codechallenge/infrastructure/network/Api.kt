package com.excelsior.codechallenge.infrastructure.network

import com.excelsior.codechallenge.infrastructure.network.data.EventDTO
import retrofit2.http.GET

interface Api {

    @GET("events")
    suspend fun getEvents(): List<EventDTO>
}