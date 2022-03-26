package com.excelsior.codechallenge.infrastructure.model.repository

data class FilterOptions(
    val sortType: SortType? = SortType.Ascending(SortType.Type.ticket_price),
    val needToShowOutDated: Boolean? = true
)

sealed class SortType {
    data class Ascending(val byField: Type) : SortType()
    data class Descending(val byField: Type) : SortType()

    enum class Type {
        date,
        ticket_price,
    }
}
