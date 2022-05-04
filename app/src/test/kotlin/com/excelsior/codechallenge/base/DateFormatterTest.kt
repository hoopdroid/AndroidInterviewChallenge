package com.excelsior.codechallenge.base

import com.excelsior.codechallenge.base.utils.DateFormatter
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class DateFormatterTest {
    @Test
    fun `test if date was correctly parsed`() {
        val dateTime = DateTime(2022, 3, 1, 13, 0)
        Assert.assertEquals("01 March 2022", DateFormatter.print(dateTime))
    }
}
