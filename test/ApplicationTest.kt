package com.checkout

import io.ktor.http.*
import io.ktor.server.testing.*
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            with(handleRequest(HttpMethod.Post, "/checkout") {
                addHeader("content-type", "application/json")
                addHeader("Accept", "application/json")
                setBody(
                    """["001","002","001","004","003"]"""
                )
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                JSONAssert.assertEquals("""{"price": 100 }""", response.content, JSONCompareMode.LENIENT)
            }
        }
    }
}
