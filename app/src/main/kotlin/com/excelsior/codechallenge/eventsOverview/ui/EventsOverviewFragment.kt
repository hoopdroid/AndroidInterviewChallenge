package com.excelsior.codechallenge.eventsOverview.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventsOverviewBinding
import com.excelsior.codechallenge.eventsOverview.ui.adapter.EventsAdapter
import com.excelsior.codechallenge.infrastructure.model.Event
import com.excelsior.codechallenge.infrastructure.ui.BaseFragment
import com.excelsior.codechallenge.infrastructure.utils.UiUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsOverviewFragment : BaseFragment<EventsOverviewViewModel, EventsOverviewBinding>() {

    override val layoutId: Int = R.layout.events_overview

    override val viewModel: EventsOverviewViewModel by viewModel<EventsOverviewAndroidViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventsAdapter = EventsAdapter(viewModel)

        binding.eventList.apply {
            setHasFixedSize(true)
            addItemDecoration(UiUtils.getDividerIconDecoration(requireContext()))
            adapter = eventsAdapter
            eventsAdapter.setItems(getEvents())
        }

        binding.apply {
            title.text = String.format(
                requireContext().getString(R.string.events_overview_title),
                "'fromDate'",
                "'untilDate'"
            )
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsOverviewViewModel) {
        binding.viewModel = viewModel
    }

    private fun getEvents(): List<Event> =
        listOf(
            Event("1", "Event 1", "01.11.2021", null),
            Event("2", "Event 2", "02.11.2021", null),
            Event("3", "Event 3", "03.11.2021", null),
            Event("4", "Event 4", "04.11.2021", null),
            Event("5", "Event 5", "05.11.2021", null),
            Event("6", "Event 6", "06.11.2021", null),
            Event("7", "Event 7", "07.11.2021", null),
            Event("8", "Event 8", "08.11.2021", null),
            Event("9", "Event 9", "09.11.2021", null),
            Event("10", "Event 10", "10.11.2021", null),
            Event("11", "Event 11", "11.11.2021", null),
            Event("12", "Event 12", "12.11.2021", null),
            Event("13", "Event 13", "13.11.2021", null),
            Event("14", "Event 14", "14.11.2021", null)
        )
}