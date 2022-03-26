package com.excelsior.codechallenge.eventsOverview.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventsOverviewBinding
import com.excelsior.codechallenge.eventsOverview.ui.adapter.EventsAdapter
import com.excelsior.codechallenge.infrastructure.ui.BaseFragment
import com.excelsior.codechallenge.infrastructure.utils.UiUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsOverviewFragment : BaseFragment<EventsOverviewViewModel, EventsOverviewBinding>() {

    override val layoutId: Int = R.layout.events_overview

    override val viewModel: EventsOverviewViewModel by viewModel<EventsOverviewAndroidViewModel>()

    private var eventsAdapter: EventsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsAdapter = EventsAdapter(viewModel)

        binding.eventList.apply {
            setHasFixedSize(true)
            addItemDecoration(UiUtils.getDividerIconDecoration(requireContext()))
            adapter = eventsAdapter
        }

        binding.apply {
            title.text = String.format(
                requireContext().getString(R.string.events_overview_title),
                "'fromDate'",
                "'untilDate'"
            )
        }

        observeEvents()
    }

    private fun observeEvents() {
        viewModel.observeEvents().observe(viewLifecycleOwner) {
            eventsAdapter?.setItems(it)
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsOverviewViewModel) {
        binding.viewModel = viewModel
    }
}