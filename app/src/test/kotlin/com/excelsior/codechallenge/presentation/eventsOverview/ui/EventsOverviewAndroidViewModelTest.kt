package com.excelsior.codechallenge.presentation.eventsOverview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.excelsior.codechallenge.base.TestCoroutineRule
import com.excelsior.codechallenge.domain.EventsInteractor
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventData
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventTimeRange
import com.excelsior.codechallenge.presentation.eventsOverview.ui.model.EventsOverviewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.isA
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.HttpException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventsOverviewViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val interactor: EventsInteractor = mock()
    private val observer: Observer<EventsOverviewState> = mock()
    private val mockData = EventData(EventTimeRange("", ""), emptyList())

    private lateinit var viewModel: EventsOverviewAndroidViewModel

    @Before
    fun setUp() {
        viewModel = EventsOverviewAndroidViewModel(interactor)
        viewModel.observeEvents().observeForever(observer)
    }

    @Test
    fun `test when fetching events ok then switch to loaded state`() {
        testCoroutineRule.runBlockingTest {
            whenever(interactor.loadEvents(isA())).thenReturn(mockData)
            viewModel.fetchEvents()
            verify(interactor).loadEvents(isA())
            verify(observer).onChanged(
                isA<EventsOverviewState.EventsLoaded>()
            )
        }
    }

    @Test
    fun `test when fetching events ok then switch to loading`() {
        testCoroutineRule.runBlockingTest {
            whenever(interactor.loadEvents(isA())).thenReturn(mockData)
            viewModel.fetchEvents()
            verify(observer).onChanged(EventsOverviewState.Loading)
        }
    }

    @Test
    fun `test when fetching events failed then switch to error`() {
        val exception: HttpException = mock()
        testCoroutineRule.runBlockingTest {
            whenever(interactor.loadEvents(isA())).thenAnswer { exception }
            viewModel.fetchEvents()
            verify(observer).onChanged(EventsOverviewState.Error)
        }
    }

    @After
    fun tearDown() {
        viewModel.observeEvents().removeObserver(observer)
    }
}
