package com.excelsior.codechallenge.data.network.di

import com.excelsior.codechallenge.data.network.adapter.DateTimeTypeMoshiAdapter
import com.excelsior.codechallenge.data.network.Api
import com.excelsior.codechallenge.data.network.gateway.ApiGateway
import com.excelsior.codechallenge.data.network.gateway.ApiRemoteGateway
import com.excelsior.codechallenge.data.network.interceptors.HeadersInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkInjectionModule {
    private const val API_BASE_URL = "https://technical-interview.excels.io/"

    val module = module {
        single<ApiGateway> {
            ApiRemoteGateway(apiService)
        }
    }

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

}