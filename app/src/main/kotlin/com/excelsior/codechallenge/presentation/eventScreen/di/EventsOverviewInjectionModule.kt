package com.excelsior.codechallenge.presentation.eventScreen.di

import com.excelsior.codechallenge.presentation.eventScreen.ui.EventScreenAndroidViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object EventsScreenInjectionModule {

    val module = module {
        viewModel {
            EventScreenAndroidViewModel(get())
        }
    }
}