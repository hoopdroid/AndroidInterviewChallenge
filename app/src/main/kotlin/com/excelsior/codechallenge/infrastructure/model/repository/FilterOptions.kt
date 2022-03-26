package com.excelsior.codechallenge.infrastructure.model.repository

data class FilterOptions(
    val fieldType: FieldType = FieldType.PRICE,
    val sortType: SortType = SortType.Ascending,
    val needToShowOutDated: Boolean? = true
)

sealed class SortType {
    object Ascending : SortType()
    object Descending : SortType()
}

sealed class FieldType(val type: String) {
    object DATE: FieldType("date")
    object PRICE: FieldType("ticket_price")
}
