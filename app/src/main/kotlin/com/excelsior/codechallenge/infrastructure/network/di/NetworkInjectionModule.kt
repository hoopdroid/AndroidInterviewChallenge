package com.excelsior.codechallenge.infrastructure.network.di

import com.excelsior.codechallenge.infrastructure.network.ApiService
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiGateway
import com.excelsior.codechallenge.infrastructure.network.gateway.ApiRemoteGateway
import org.koin.dsl.module

object NetworkInjectionModule {

    val module = module {

        single {
            ApiService()
        }

        single<ApiGateway> {
            ApiRemoteGateway(get(), get())
        }
    }
}