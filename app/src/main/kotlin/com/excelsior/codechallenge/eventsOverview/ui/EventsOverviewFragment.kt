package com.excelsior.codechallenge.eventsOverview.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.databinding.ViewDataBinding
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventsOverviewBinding
import com.excelsior.codechallenge.eventsOverview.ui.adapter.EventsAdapter
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.infrastructure.model.repository.SortType
import com.excelsior.codechallenge.infrastructure.ui.BaseFragment
import com.excelsior.codechallenge.infrastructure.utils.UiUtils
import com.excelsior.codechallenge.infrastructure.utils.gone
import com.excelsior.codechallenge.infrastructure.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsOverviewFragment : BaseFragment<EventsOverviewViewModel, EventsOverviewBinding>() {

    override val layoutId: Int = R.layout.events_overview

    override val viewModel: EventsOverviewViewModel by viewModel<EventsOverviewAndroidViewModel>()

    private var eventsAdapter: EventsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsAdapter = EventsAdapter(viewModel)

        initUI()
        observeScreenState()
    }

    private fun initUI() {
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

        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter -> {
                    viewModel.fetchEvents(inputType = EventsInputType.SORT)
                    true
                }
                R.id.action_field -> {
                    viewModel.fetchEvents(inputType = EventsInputType.FIELD)
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }

        }
    }

    private fun observeScreenState() {
        viewModel.observeEvents().observe(viewLifecycleOwner) { state ->

            when (state) {
                is EventsOverviewState.Error -> {
                    binding.eventList.gone()
                    binding.progress.gone()
                    // todo error text
                }
                is EventsOverviewState.EventsLoaded -> {
                    binding.progress.gone()
                    binding.eventList.show()
                    eventsAdapter?.setItems(state.eventsList)
                    renderToolbar(state.sortType)
                }
                is EventsOverviewState.Loading -> {
                    binding.eventList.gone()
                    binding.progress.show()
                }
            }
        }
    }

    private fun renderToolbar(sortType: SortType) {
        val filterMenuItem = binding.toolbar.menu.findItem(R.id.action_filter)
        val typeMenuItem = binding.toolbar.menu.findItem(R.id.action_field)

        when (sortType) {
            is SortType.Ascending -> {
                filterMenuItem.setIcon(R.drawable.ic_filter_ascending)
                if (sortType.byField == SortType.Type.ticket_price) {
                    typeMenuItem.setIcon(R.drawable.ic_baseline_attach_money_24)
                } else {
                    typeMenuItem.setIcon(R.drawable.ic_baseline_today_24)
                }
            }
            is SortType.Descending -> {
                filterMenuItem.setIcon(R.drawable.ic_filter_descending)

                if (sortType.byField == SortType.Type.ticket_price) {
                    typeMenuItem.setIcon(R.drawable.ic_baseline_attach_money_24)
                } else {
                    typeMenuItem.setIcon(R.drawable.ic_baseline_today_24)
                }
            }
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsOverviewViewModel) {
        binding.viewModel = viewModel
    }
}