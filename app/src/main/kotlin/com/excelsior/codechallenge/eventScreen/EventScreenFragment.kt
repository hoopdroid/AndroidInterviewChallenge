package com.excelsior.codechallenge.eventScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventScreenBinding
import com.excelsior.codechallenge.infrastructure.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventScreenFragment : BaseFragment<EventsScreenViewModel, EventScreenBinding>() {
    override val layoutId: Int = R.layout.event_screen
    override val viewModel: EventsScreenViewModel by viewModel<EventsScreenAndroidViewModel>()

    private val args: EventScreenFragmentArgs by navArgs<EventScreenFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEvent(args.id)

        observeScreenState()
    }

    private fun observeScreenState() {
        viewModel.observeEvent().observe(viewLifecycleOwner) {
            binding.event = it
        }
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventsScreenViewModel) {
        binding.viewModel = viewModel
    }
}