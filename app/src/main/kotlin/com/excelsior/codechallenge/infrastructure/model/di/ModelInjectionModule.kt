package com.excelsior.codechallenge.infrastructure.model.di

import com.excelsior.codechallenge.infrastructure.model.EventMapper
import org.koin.dsl.module

object ModelInjectionModule {

    val module = module {

        factory {
            EventMapper()
        }
    }
}