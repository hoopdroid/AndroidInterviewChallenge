package com.excelsior.codechallenge.eventScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventScreenBinding
import com.excelsior.codechallenge.infrastructure.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventScreenFragment : BaseFragment<EventsScreenViewModel, EventScreenBinding>() {
    override val layoutId: Int = R.layout.event_screen
    override val viewModel: EventsScreenViewModel by viewModel<EventsScreenAndroidViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        observeScreenState()
    }

    private fun initUI() {
    }

    private fun observeScreenState() {
        viewModel.getEvent(arguments?.getString("id") ?: "no")

        viewModel.observeEvent().observe(viewLifecycleOwner) {
            binding.event = it
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsScreenViewModel) {
        binding.viewModel = viewModel
    }
}