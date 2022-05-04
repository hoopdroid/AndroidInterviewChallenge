package com.excelsior.codechallenge.presentation.eventScreen.ui

sealed class EventScreenState {
    object Loading : EventScreenState()
    data class Error(val message: String? = null) : EventScreenState()
    data class EventsLoaded(val event: EventItemVO
    ) : EventScreenState()
}