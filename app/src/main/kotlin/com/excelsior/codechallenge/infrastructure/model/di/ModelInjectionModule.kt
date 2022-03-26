package com.excelsior.codechallenge.infrastructure.model.di

import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.model.repository.EventListDataSource
import com.excelsior.codechallenge.infrastructure.model.repository.EventDataSource
import org.koin.dsl.module

object ModelInjectionModule {

    val module = module {

        single {
            EventMapper()
        }

        single<EventDataSource> {
            EventListDataSource(get(), get())
        }
    }
}