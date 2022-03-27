package com.excelsior.codechallenge.infrastructure.domain

import com.excelsior.codechallenge.infrastructure.model.repository.EventData
import com.excelsior.codechallenge.infrastructure.model.repository.FilterOptions

interface EventsListUseCase {
    suspend fun invoke(filterOptions: FilterOptions): EventData
}