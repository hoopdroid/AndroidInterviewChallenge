package com.excelsior.codechallenge.infrastructure.domain.di

import com.excelsior.codechallenge.infrastructure.domain.EventListInteractor
import com.excelsior.codechallenge.infrastructure.domain.EventListInteractorImpl
import com.excelsior.codechallenge.infrastructure.mapper.EventMapper
import org.koin.dsl.module

object DomainInjectionModule {

    val module = module {
        single {
            EventMapper()
        }

        single<EventListInteractor> {
            EventListInteractorImpl(get(), get())
        }
    }
}