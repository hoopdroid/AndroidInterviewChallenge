package com.excelsior.codechallenge.infrastructure.model.di

import com.excelsior.codechallenge.infrastructure.model.EventMapper
import com.excelsior.codechallenge.infrastructure.model.repository.EventListRepository
import com.excelsior.codechallenge.infrastructure.model.repository.EventRepository
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiRemoteGateway
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter
import org.koin.dsl.module

object ModelInjectionModule {

    val module = module {

        single {
            EventMapper()
        }

        single<EventRepository> {
            EventListRepository(get())
        }
    }
}