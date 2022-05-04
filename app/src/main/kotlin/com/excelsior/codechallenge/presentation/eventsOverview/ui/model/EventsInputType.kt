package com.excelsior.codechallenge.presentation.eventsOverview.ui.model


sealed class EventsInputType {
    object FieldFilter : EventsInputType()
    object SortFilter : EventsInputType()
    object ShowOutdatedFilter : EventsInputType()
}
