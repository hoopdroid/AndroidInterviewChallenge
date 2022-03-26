package com.excelsior.codechallenge.eventsOverview.ui.model


sealed class EventsInputType {
    object FIELD : EventsInputType()
    object SORT : EventsInputType()
}
