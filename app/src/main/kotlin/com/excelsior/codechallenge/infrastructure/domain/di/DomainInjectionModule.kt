package com.excelsior.codechallenge.infrastructure.domain.di

import com.excelsior.codechallenge.infrastructure.domain.EventsInteractor
import com.excelsior.codechallenge.infrastructure.domain.EventsListInteractor
import com.excelsior.codechallenge.infrastructure.mapper.EventMapper
import org.koin.dsl.module

object DomainInjectionModule {

    val module = module {
        single {
            EventMapper()
        }

        single<EventsInteractor> {
            EventsListInteractor(get(), get())
        }
    }
}