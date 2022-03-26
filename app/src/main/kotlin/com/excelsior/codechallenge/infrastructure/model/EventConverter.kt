package com.excelsior.codechallenge.infrastructure.model

class EventConverter {

    fun fromSource(source: Map<String, Any?>): Event =
        Event(
            id = (source["guid"] as? String).orEmpty(),
            name = (source["event"] as? String).orEmpty(),
            formattedDate = (source["date"] as? String).orEmpty(),
            date = null
        )
}