package com.excelsior.codechallenge.infrastructure.network

import com.excelsior.codechallenge.infrastructure.network.data.EventDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("events")
    suspend fun getEvents(): List<EventDTO>

    @GET("event/{id}")
    suspend fun getEvent(@Path("id") id: String,): EventDTO
}