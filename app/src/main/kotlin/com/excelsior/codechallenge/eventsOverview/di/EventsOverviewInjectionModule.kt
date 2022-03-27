package com.excelsior.codechallenge.eventsOverview.di

import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewAndroidViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object EventsOverviewInjectionModule {

    val module = module {

        viewModel {
            EventsOverviewAndroidViewModel(get())
        }
    }
}