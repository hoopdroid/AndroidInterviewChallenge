package com.excelsior.codechallenge.data.di

import com.excelsior.codechallenge.data.repository.EventListDataSource
import com.excelsior.codechallenge.data.repository.EventDataSource
import org.koin.dsl.module

object ModelInjectionModule {
    val module = module {
        single<EventDataSource> {
            EventListDataSource(get())
        }
    }
}