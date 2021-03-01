package com.checkout

import io.ktor.http.*
import io.ktor.server.testing.*
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun should_not_apply_any_discount() {
        withTestApplication({ module() }) {
            with(handleRequest(HttpMethod.Post, "/checkout") {
                addHeader("content-type", "application/json")
                addHeader("Accept", "application/json")
                setBody("""["001","002","001","004","003"]""")
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                JSONAssert.assertEquals("""{"price": 360 }""", response.content, JSONCompareMode.LENIENT)
            }
        }
    }

    @Test
    fun should_apply_rolex_and_michael_kors_discount() {
        withTestApplication({ module() }) {
            with(handleRequest(HttpMethod.Post, "/checkout") {
                addHeader("content-type", "application/json")
                addHeader("Accept", "application/json")
                setBody("""["001","001","001","002","002"]""")
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                JSONAssert.assertEquals("""{"price": 320 }""", response.content, JSONCompareMode.LENIENT)
            }
        }
    }

    @Test
    fun should_apply_rolex_discount() {
        withTestApplication({ module() }) {
            with(handleRequest(HttpMethod.Post, "/checkout") {
                addHeader("content-type", "application/json")
                addHeader("Accept", "application/json")
                setBody("""["001","001","001","002"]""")
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                JSONAssert.assertEquals("""{"price": 280 }""", response.content, JSONCompareMode.LENIENT)
            }
        }
    }

    @Test
    fun should_apply_michael_kors_discount() {
        withTestApplication({ module() }) {
            with(handleRequest(HttpMethod.Post, "/checkout") {
                addHeader("content-type", "application/json")
                addHeader("Accept", "application/json")
                setBody("""["001","002","002","002","002"]""")
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                JSONAssert.assertEquals("""{"price": 340 }""", response.content, JSONCompareMode.LENIENT)
            }
        }
    }
}
