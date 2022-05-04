package com.excelsior.codechallenge.presentation.eventsOverview.di

import com.excelsior.codechallenge.presentation.eventsOverview.ui.EventsOverviewAndroidViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object EventsOverviewInjectionModule {
    val module = module {
        viewModel {
            EventsOverviewAndroidViewModel(get())
        }
    }
}