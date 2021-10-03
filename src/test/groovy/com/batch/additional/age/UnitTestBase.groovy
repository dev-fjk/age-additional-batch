package com.batch.additional.age

import spock.lang.Specification

import java.time.Clock
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class UnitTestBase extends Specification {

    // ZoneId
    ZoneId zoneId = ZoneId.of("Asia/Tokyo")

    // Dates
    DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyyMMdd")
    DateTimeFormatter monthDayFmt = DateTimeFormatter.ofPattern("MMdd")

    Clock mockClock() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                2021, 10, 3,
                18, 15, 0, 9999, zoneId
        )

        return Clock.fixed(zonedDateTime.toInstant(), zoneId)
    }
}
