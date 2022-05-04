package com.excelsior.codechallenge.domain.di

import com.excelsior.codechallenge.domain.EventsInteractor
import com.excelsior.codechallenge.domain.EventsListInteractor
import com.excelsior.codechallenge.domain.mapper.EventMapper
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