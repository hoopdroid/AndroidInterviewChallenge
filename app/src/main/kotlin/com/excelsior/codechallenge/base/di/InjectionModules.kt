package com.excelsior.codechallenge.base.di

import com.excelsior.codechallenge.presentation.eventScreen.di.EventsScreenInjectionModule
import com.excelsior.codechallenge.presentation.eventsOverview.di.EventsOverviewInjectionModule
import com.excelsior.codechallenge.domain.di.DomainInjectionModule
import com.excelsior.codechallenge.data.di.ModelInjectionModule
import com.excelsior.codechallenge.data.network.di.NetworkInjectionModule

object InjectionModules {
    val modules = listOf(
        EventsOverviewInjectionModule.module,
        EventsScreenInjectionModule.module,
        ModelInjectionModule.module,
        DomainInjectionModule.module,
        NetworkInjectionModule.module
    )
}