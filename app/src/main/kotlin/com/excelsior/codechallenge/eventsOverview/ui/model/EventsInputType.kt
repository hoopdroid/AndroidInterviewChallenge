package com.excelsior.codechallenge.eventsOverview.ui.model


sealed class EventsInputType {
    object FieldFilter : EventsInputType()
    object SortFilter : EventsInputType()
    object ShowOutdatedFilter : EventsInputType()
}
