package com.auru.mobilunity

import com.auru.mobilunity.dto.RepoElement
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val gson = Gson()

    val testResult1 =
            "{"+
            "\"name\": \"yajl-objc\","+
            "\"owner\": {"+
            "\"login\": \"square\""+
            "},"+
            "\"description\": \"Objective-C bindings for YAJL (Yet Another JSON Library) C library\","+
            "\"fork\": true"+
            "}"

    val testResult2 = "["+
        "{"+
            "\"name\": \"yajl-objc\","+
            "\"owner\": {"+
            "\"login\": \"square\""+
    "},"+
    "\"description\": \"Objective-C bindings for YAJL (Yet Another JSON Library) C library\","+
            "\"fork\": true"+
        "},"+
        "{"+
            "\"name\": \"simplerrd\","+
            "\"owner\": {"+
            "\"login\": \"square\""+
        "},"+
            "\"description\": \"SimpleRRD provides a simple Ruby interface for creating graphs with RRD\","+
            "\"fork\": false"+
        "}]"

    val expectedElement1 = RepoElement(
        "yajl-objc",
        "Objective-C bindings for YAJL (Yet Another JSON Library) C library"
    )
    val expectedElement2 = RepoElement(
        "simplerrd",
        "SimpleRRD provides a simple Ruby interface for creating graphs with RRD"
    )

    @Test
    fun parse1() {
        val el = gson.fromJson<RepoElement>(testResult1, RepoElement::class.java)
        assertEquals(expectedElement1, el)
    }

    @Test
    fun parse2() {
        val list = gson.fromJson<Array<RepoElement>>(testResult2, Array<RepoElement>::class.java)
        assertEquals(expectedElement1, list[0])
        assertEquals(expectedElement2, list[1])
    }
}
