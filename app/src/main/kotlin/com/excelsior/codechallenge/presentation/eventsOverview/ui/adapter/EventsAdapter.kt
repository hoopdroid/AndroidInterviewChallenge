package com.excelsior.codechallenge.presentation.eventsOverview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.excelsior.codechallenge.databinding.EventListItemBinding
import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventsOverviewViewModel
import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventVO

class EventsAdapter(
    private val viewModel: EventsOverviewViewModel
) : RecyclerView.Adapter<EventViewHolder>() {

    private val items = mutableListOf<EventVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            viewModel,
            EventListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int =
        items.size

    fun setItems(newData: List<EventVO>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }
}