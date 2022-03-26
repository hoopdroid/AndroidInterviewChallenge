package com.excelsior.codechallenge.eventScreen.di

import com.excelsior.codechallenge.eventScreen.EventsScreenAndroidViewModel
import com.excelsior.codechallenge.eventsOverview.ui.EventsOverviewAndroidViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object EventsScreenInjectionModule {

    val module = module {

        viewModel {
            EventsScreenAndroidViewModel()
        }
    }
}