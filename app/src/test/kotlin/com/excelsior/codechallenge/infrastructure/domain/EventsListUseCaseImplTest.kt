package com.excelsior.codechallenge.infrastructure.domain

import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.model.repository.EventDataSource
import com.excelsior.codechallenge.infrastructure.model.repository.FilterOptions
import com.excelsior.codechallenge.base.sortedMockEventList
import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.lang.Exception

@RunWith(JUnit4::class)
class EventsListUseCaseImplTest {
    private val eventDataSource: EventDataSource = mock()
    private val eventMapper: EventMapper = EventMapper()
    private val filterOptions: FilterOptions = FilterOptions()
    private val mockUseCase = EventsListUseCaseImpl(eventDataSource, eventMapper)

    @Test
    fun `test if start and end dates are correctly resolved`() {
        runBlocking {
            whenever(eventDataSource.getEvents(filterOptions)).thenAnswer { sortedMockEventList }

            val eventsList = sortedMockEventList.map { eventMapper.toVO(it) }
            val eventTimeRange = mockUseCase.invoke(filterOptions).eventTimeRange

            Assert.assertEquals(eventTimeRange.fromDate, eventsList.first().formattedDate)
            Assert.assertEquals(eventTimeRange.untilDate, eventsList.last().formattedDate)
        }
    }


    @Test
    fun `test when events list are empty exception was thrown`() {
        runBlocking {
            whenever(eventDataSource.getEvents(filterOptions)).thenAnswer { emptyList<EventsDTO>() }

            try {
                mockUseCase.invoke(filterOptions).eventTimeRange
            } catch (parseException: Exception) {
                Assert.assertNotNull(parseException)
            }
        }
    }
}