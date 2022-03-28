package com.excelsior.codechallenge.eventsOverview.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventsOverviewBinding
import com.excelsior.codechallenge.eventsOverview.ui.adapter.EventsAdapter
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsInputType
import com.excelsior.codechallenge.eventsOverview.ui.model.EventsOverviewState
import com.excelsior.codechallenge.eventsOverview.ui.model.FieldType
import com.excelsior.codechallenge.eventsOverview.ui.model.FilterOptions
import com.excelsior.codechallenge.eventsOverview.ui.model.SortType
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

    private fun observeScreenState() {
        viewModel.observeEvents().observe(viewLifecycleOwner) { state ->

            when (state) {
                is EventsOverviewState.Error -> {
                    binding.eventList.gone()
                    binding.swipeRefreshContainer.isRefreshing = false
                    binding.title.text = requireContext().getString(R.string.event_fetch_error)
                }
                is EventsOverviewState.EventsLoaded -> {
                    binding.title.text = String.format(
                        requireContext().getString(R.string.events_overview_title),
                        state.eventsTimeRange.fromDate,
                        state.eventsTimeRange.untilDate
                    )
                    binding.swipeRefreshContainer.isRefreshing = false
                    binding.eventList.show()
                    eventsAdapter?.setItems(state.eventsList)
                    renderToolbar(state.filterOptions)
                }
                is EventsOverviewState.Loading -> {
                    binding.eventList.gone()
                    binding.swipeRefreshContainer.isRefreshing = true
                }
            }
        }
    }


    private fun initUI() {
        binding.eventList.apply {
            setHasFixedSize(true)
            addItemDecoration(UiUtils.getDividerIconDecoration(requireContext()))
            adapter = eventsAdapter
        }

        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter -> {
                    viewModel.fetchEvents(inputType = EventsInputType.SortFilter)
                    true
                }
                R.id.action_field -> {
                    viewModel.fetchEvents(inputType = EventsInputType.FieldFilter)
                    true
                }
                R.id.action_outdated -> {
                    viewModel.fetchEvents(inputType = EventsInputType.ShowOutdatedFilter)
                    true
                }
                else -> super.onOptionsItemSelected(it)
            }

        }
        binding.swipeRefreshContainer.setOnRefreshListener { viewModel.fetchEvents() }
    }


    private fun renderToolbar(filterOptions: FilterOptions) {
        val filterMenuItem = binding.toolbar.menu.findItem(R.id.action_filter)
        val typeMenuItem = binding.toolbar.menu.findItem(R.id.action_field)
        val filterOutDatedItem = binding.toolbar.menu.findItem(R.id.action_outdated)

        when (filterOptions.sortType) {
            is SortType.Ascending -> filterMenuItem.setIcon(R.drawable.ic_filter_ascending)
            is SortType.Descending -> filterMenuItem.setIcon(R.drawable.ic_filter_descending)
        }

        when (filterOptions.fieldType) {
            is FieldType.PRICE -> typeMenuItem.setIcon(R.drawable.ic_baseline_attach_money_24)
            is FieldType.DATE -> typeMenuItem.setIcon(R.drawable.ic_baseline_today_24)
        }

        if (filterOptions.needToShowOutDated == true) {
            filterOutDatedItem.setIcon(R.drawable.ic_baseline_undo_24)
        } else {
            filterOutDatedItem.setIcon(R.drawable.ic_baseline_redo_24)
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsOverviewViewModel) {
        binding.viewModel = viewModel
    }
}