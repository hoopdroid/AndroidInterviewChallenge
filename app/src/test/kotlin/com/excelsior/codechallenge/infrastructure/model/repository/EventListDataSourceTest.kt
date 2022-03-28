package com.excelsior.codechallenge.infrastructure.model.repository

import com.excelsior.codechallenge.base.BaseMockResponseTest
import com.excelsior.codechallenge.base.eventListFilteredByPriceDesc
import com.excelsior.codechallenge.base.sortedMockEventList
import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class EventDataSourceTest : BaseMockResponseTest() {
    private lateinit var eventsDataSource: EventDataSource
    private lateinit var apiService: ApiGateway

    @Before
    fun start() {
        apiService = provideTestApiService()
        eventsDataSource = EventListDataSource(apiService)
    }

    @Test
    fun `given response ok when fetching results then return a list with elements`() {
        runBlocking {
            mockResponse("json/response_items.json", HttpURLConnection.HTTP_OK)
            val apiResponse = eventsDataSource.getEvents(FilterOptions())

            assertNotNull(apiResponse)
            assertEquals(apiResponse.size, sortedMockEventList.size)
        }
    }

    @Test
    fun `given response ok when fetching empty results then return an empty list`() {
        runBlocking {
            mockResponse("json/response_empty_list.json", HttpURLConnection.HTTP_OK)
            val apiResponse = eventsDataSource.getEvents(FilterOptions())

            assertNotNull(apiResponse)
            assertEquals(apiResponse.size, 0)
        }
    }

    @Test
    fun `given failured response when parsing json`() {
        runBlocking {
            mockResponse("json/response_incorrect_items.json", HttpURLConnection.HTTP_OK)
            try {
                eventsDataSource.getEvents(FilterOptions())
            } catch (exception: JsonDataException) {
                assertNotNull(exception)
            }
        }
    }

    @Test
    fun `given response ok when filter is by price then return sorted by price list ascending`() {
        runBlocking {
            mockResponse("json/response_items.json")
            val givePricesSort =
                eventsDataSource.getEvents(FilterOptions(FieldType.PRICE, SortType.Ascending))
            val expectedPricesSort = sortedMockEventList

            assertEquals(givePricesSort, expectedPricesSort)
        }
    }


    @Test
    fun `given response ok when filter is by date then return sorted by date list ascending`() {
        runBlocking {
            mockResponse("json/response_items.json")
            val givePricesSort =
                eventsDataSource.getEvents(FilterOptions(FieldType.DATE, SortType.Ascending))
            val expectedPricesSort = sortedMockEventList

            assertEquals(givePricesSort, expectedPricesSort)
        }
    }

    @Test
    fun `given response ok when filter is by price then return sorted by price list descending`() {
        runBlocking {
            mockResponse("json/response_items.json")
            val givePricesSort =
                eventsDataSource.getEvents(FilterOptions(FieldType.PRICE, SortType.Descending))
            val expectedPricesSort = eventListFilteredByPriceDesc

            assertEquals(givePricesSort, expectedPricesSort)
        }
    }

    @Test
    fun `given response ok when filter is by date then return sorted by date list descending`() {
        runBlocking {
            mockResponse("json/response_items.json")
            val givePricesSort =
                eventsDataSource.getEvents(FilterOptions(FieldType.DATE, SortType.Descending))
            val expectedPricesSort = eventListFilteredByPriceDesc

            assertEquals(givePricesSort, expectedPricesSort)
        }
    }
}
