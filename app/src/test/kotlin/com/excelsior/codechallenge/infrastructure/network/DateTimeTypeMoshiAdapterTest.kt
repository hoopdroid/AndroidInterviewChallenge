package com.excelsior.codechallenge.infrastructure.network

import com.excelsior.codechallenge.infrastructure.network.adapter.DateTimeTypeMoshiAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DateTimeTypeMoshiAdapterTest {
    private val moshi = Moshi.Builder()
        .add(DateTimeTypeMoshiAdapter())
        .addLast((KotlinJsonAdapterFactory()))
        .build()
    private val testDateJson = "{\"date\":\"2023-01-21T22:22:22+03:00\"}"
    private val dateTime = DateTime(2023, 1, 21, 22, 22, 22)

    private data class TestDateObject(val date: DateTime)

    @Test
    fun `test if json encoded from given type`() {
        val testObject = TestDateObject(dateTime)
        val testJsonString = moshi.adapter(TestDateObject::class.java).toJson(testObject)
        Assert.assertEquals(testJsonString, testDateJson)
    }

    @Test
    fun `test if datetime parsed from json`() {
        val testObj =
            moshi.adapter(TestDateObject::class.java).fromJson(testDateJson)

        Assert.assertEquals(dateTime, testObj?.date)
    }
}