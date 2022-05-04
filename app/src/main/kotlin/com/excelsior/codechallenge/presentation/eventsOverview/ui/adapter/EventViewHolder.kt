package com.excelsior.codechallenge.presentation.eventsOverview.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.excelsior.codechallenge.databinding.EventListItemBinding
import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventsOverviewViewModel
import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventsOverviewFragmentDirections

class EventViewHolder(
    private val viewModel: EventsOverviewViewModel,
    private val binding: EventListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: EventVO) {
        binding.also {
            it.event = event
            it.viewModel = viewModel
        }
        itemView.setOnClickListener {
            viewModel.direction.value =
                EventsOverviewFragmentDirections.actionEventsOverviewToEventScreenFragment()
                    .apply { id = event.id }
        }
    }
}