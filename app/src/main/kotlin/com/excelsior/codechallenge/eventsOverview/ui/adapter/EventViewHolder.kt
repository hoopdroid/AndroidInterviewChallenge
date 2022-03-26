package com.excelsior.codechallenge.eventsOverview.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.excelsior.codechallenge.databinding.EventListItemBinding
import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewViewModel
import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter

class EventViewHolder(
    private val viewModel: EventsOverviewViewModel,
    private val binding: EventListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: EventVO) {
        binding.also {
            it.event = event
            it.viewModel = viewModel
        }
    }
}