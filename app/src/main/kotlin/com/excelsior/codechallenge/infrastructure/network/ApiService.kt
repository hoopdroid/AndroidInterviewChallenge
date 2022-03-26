package com.excelsior.codechallenge.infrastructure.network

import com.excelsior.codechallenge.eventsOverview.ui.DateTimeTypeMoshiAdapter
import com.excelsior.codechallenge.infrastructure.network.interceptors.HeadersInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.addAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class ApiService {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val headersInterceptor = HeadersInterceptor()

    private val clientBuilder = OkHttpClient.Builder()
        .apply {
            addNetworkInterceptor(logging)
            addInterceptor(headersInterceptor)
        }

    private val moshi = Moshi.Builder()
        .add(DateTimeTypeMoshiAdapter())
        .addLast((KotlinJsonAdapterFactory()))
        .build()

    private val apiService = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(clientBuilder.build())
        .build()
        .create(Api::class.java)

    fun get(): Api =
        apiService

    private companion object {

        const val API_BASE_URL = "https://technical-interview.excels.io/"
    }
}