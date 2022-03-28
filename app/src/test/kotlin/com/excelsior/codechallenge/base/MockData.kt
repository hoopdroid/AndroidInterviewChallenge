package com.excelsior.codechallenge.base

import com.excelsior.codechallenge.infrastructure.network.data.EventsDTO
import org.joda.time.DateTime

//[
//{
//    "guid": "d051e1be-adba-46be-81d0-2edb9ccb6f24",
//    "event": "Pyramax Oceanica",
//    "ticketPrice": 369.92,
//    "date": "2022-02-12T10:47:36.797Z"
//},
//{
//    "guid": "4c4f80ea-d9f1-4345-a426-ae4f31f93426",
//    "event": "Zenolux Earthmark",
//    "ticketPrice": 864.45,
//    "date": "2022-06-28T10:47:36.797Z"
//},
//{
//    "guid": "04ea0d2f-6c38-43f3-9eb9-da072ad763bc",
//    "event": "Qualitern Lotron",
//    "ticketPrice": 937.61,
//    "date": "2022-11-15T10:47:36.797Z"
//},
//{
//    "guid": "75990293-2829-458f-84c6-e79830f58d12",
//    "event": "Nutralab Noralex",
//    "ticketPrice": 851.12,
//    "date": "2023-01-21T10:47:36.797Z"
//}
//]


val sortedMockEventList = listOf(
    EventsDTO(
        guid = "1",
        event = "1",
        ticketPrice = 100.92,
        date = DateTime.parse("2022-02-12T10:47:36.79")
    ),
    EventsDTO(
        guid = "2",
        event = "2",
        ticketPrice = 200.45,
        date = DateTime.parse("2022-06-28T10:47:36.79")
    ),
    EventsDTO(
        guid = "3",
        event = "3",
        ticketPrice = 300.61,
        date = DateTime.parse("2022-11-15T10:47:36.79")
    ),
    EventsDTO(
        guid = "4",
        event = "4",
        ticketPrice = 400.12,
        date = DateTime.parse("2023-01-21T10:47:36.79")
    ),
)

val eventListFilteredByPriceDesc = sortedMockEventList.asReversed()
