package com.excelsior.codechallenge.infrastructure.domain.di

import com.excelsior.codechallenge.infrastructure.domain.EventsListUseCase
import com.excelsior.codechallenge.infrastructure.domain.EventsListUseCaseImpl
import com.excelsior.codechallenge.infrastructure.model.EventMapper
import org.koin.dsl.module

object DomainInjectionModule {

    val module = module {
        single {
            EventMapper()
        }

        single<EventsListUseCase> {
            EventsListUseCaseImpl(get(), get())
        }
    }
}