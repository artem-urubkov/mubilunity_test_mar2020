package com.auru.mobilunity

import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement1
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement2
import com.auru.mobilunity.sharedData.testRepoElementsResult1
import com.auru.mobilunity.sharedData.testRepoElementsResult2
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class RepoElementsParsingUnitTest {

    val gson = Gson()

    @Test
    fun parse1() {
        val el = gson.fromJson<RepoElement>(testRepoElementsResult1, RepoElement::class.java)
        assertEquals(expectedElement1, el)
    }

    @Test
    fun parse2() {
        val list = gson.fromJson<Array<RepoElement>>(testRepoElementsResult2, Array<RepoElement>::class.java)
        assertEquals(expectedElement1, list[0])
        assertEquals(expectedElement2, list[1])
    }
}
