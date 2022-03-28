package com.excelsior.codechallenge.infrastructure.model

import com.excelsior.codechallenge.base.sortedMockEventList
import com.excelsior.codechallenge.eventScreen.ui.EventItemVO
import com.excelsior.codechallenge.eventsOverview.ui.EventVO
import com.excelsior.codechallenge.infrastructure.mapper.EventMapper
import com.excelsior.codechallenge.infrastructure.network.data.EventItemDTO
import com.excelsior.codechallenge.infrastructure.utils.DateFormatter
import org.joda.time.DateTime
import org.junit.Test

class EventMapperTest {
    private val mapper = EventMapper()

    @Test
    fun `test map from events list dto to events list vo`() {
        val dto = sortedMockEventList.first()
        val expectedVO = EventVO(
            dto.guid,
            dto.event,
            dto.ticketPrice,
            DateFormatter.print(dto.date)
        )

        assert(mapper.toVO(dto) == expectedVO)
    }

    @Test
    fun `test map from event item dto to event item vo`() {
        val dto = EventItemDTO(
            "75990293-2829-458f-84c6-e79830f58d12",
            "Nutralab Noralex",
            851.12,
            DateTime.parse("2022-02-12T10:47:36.79"),
            "Long description",
            "+7 999 999 99 99",
            "Moscow, Russia"
        )
        val expectedVO = EventItemVO(
            dto.guid,
            dto.event,
            dto.ticketPrice,
            DateFormatter.print(dto.date),
            dto.description,
            dto.address,
            dto.phone
        )

        assert(mapper.toVO(dto) == expectedVO)
    }

}