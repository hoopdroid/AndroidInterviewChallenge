package com.excelsior.codechallenge.eventScreen.ui

data class EventItemVO(
    val id: String,
    val name: String,
    val price: Double,
    val formattedDate: String,
    val description: String,
    val address: String,
    val phone: String
)