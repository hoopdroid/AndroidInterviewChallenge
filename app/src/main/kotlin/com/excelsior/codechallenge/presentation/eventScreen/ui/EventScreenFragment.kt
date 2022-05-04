package com.excelsior.codechallenge.presentation.eventScreen.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.navArgs
import com.excelsior.codechallenge.R
import com.excelsior.codechallenge.databinding.EventScreenBinding
import com.excelsior.codechallenge.base.ui.BaseFragment
import com.excelsior.codechallenge.base.utils.gone
import com.excelsior.codechallenge.base.utils.show
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventScreenFragment : BaseFragment<EventScreenViewModel, EventScreenBinding>() {
    override val layoutId: Int = R.layout.event_screen
    override val viewModel: EventScreenViewModel by viewModel<EventScreenAndroidViewModel>()

    private val args: EventScreenFragmentArgs by navArgs()
    private val onRetryClick = View.OnClickListener { fetchEvent() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEvent(args.id)
        observeScreenState()
    }

    private fun observeScreenState() {
        viewModel.observeEvent().observe(viewLifecycleOwner) { state ->
            when (state) {
                is EventScreenState.Error -> {
                    binding.container.gone()
                    showErrorMessage()
                }
                is EventScreenState.EventsLoaded -> {
                    binding.container.show()
                    binding.progress.gone()
                    binding.event = state.event
                }
                is EventScreenState.Loading -> {
                    binding.progress.show()
                }
            }
        }
    }

    private fun fetchEvent() {
        viewModel.getEvent(args.id)
    }

    private fun showErrorMessage() {
        Snackbar.make(
            binding.container,
            getString(R.string.event_fetch_error), Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction(getString(R.string.event_fetch_retry), onRetryClick)
        }.show()
    }

    override fun ViewDataBinding.setViewModel(viewModel: EventScreenViewModel) {
        binding.viewModel = viewModel
    }
}