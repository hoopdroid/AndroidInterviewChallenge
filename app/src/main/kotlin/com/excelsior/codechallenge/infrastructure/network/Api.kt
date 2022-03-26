package com.excelsior.codechallenge.infrastructure.network

import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("events")
    suspend fun getEvents(): List<EventsDTO>

    @GET("event/{id}")
    suspend fun getEvent(@Path("id") id: String,): EventItemDTO
}