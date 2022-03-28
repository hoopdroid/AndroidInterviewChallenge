package com.excelsior.codechallenge.base

import com.excelsior.codechallenge.infrastructure.network.adapter.DateTimeTypeMoshiAdapter
import com.excelsior.codechallenge.infrastructure.network.Api
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiRemoteGateway
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.net.HttpURLConnection

abstract class BaseMockResponseTest {
    private lateinit var mockServer: MockWebServer

    @Before
    fun setUp() {
        this.configureMockServer()
    }

    @After
    fun tearDown() {
        this.stopMockServer()
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    fun mockResponse(fileName: String, responseCode: Int? = null) =
        mockServer.enqueue(
            MockResponse().setResponseCode(responseCode ?: HttpURLConnection.HTTP_OK)
                .setBody(getJson(fileName))
        )

    fun provideTestApiService(): ApiGateway {
        val moshi = Moshi.Builder()
            .add(DateTimeTypeMoshiAdapter())
            .addLast((KotlinJsonAdapterFactory()))
            .build()

        return ApiRemoteGateway(
            Retrofit.Builder()
                .baseUrl(mockServer.url("/"))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(OkHttpClient.Builder().build()).build().create(Api::class.java)
        )
    }

    private fun getJson(path: String): String {
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}