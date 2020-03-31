package com.auru.mobilunity

import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement1
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement2
import com.auru.mobilunity.sharedData.testRepoElementsResult1
import com.auru.mobilunity.sharedData.testRepoElementsResult2
import com.google.gson.Gson
import org.junit.Assert.*
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
        val list = gson.fromJson<Array<RepoElement>>(
            testRepoElementsResult2,
            Array<RepoElement>::class.java
        )
        assertEquals(expectedElement1, list[0])
        assertEquals(expectedElement2, list[1])
    }

    @Test
    fun questionsAndSum() {
        assertTrue(processQestionsAndSum("arrb6???4xxbl5???eee5"))
        assertTrue(processQestionsAndSum("arrb9???4xxbl5???5eee5"))
        assertTrue(processQestionsAndSum("arrb6???4xxbl5????5eee5"))
        assertFalse(processQestionsAndSum(null))
        assertFalse(processQestionsAndSum("arrb6??4xxbl5???eee5"))
        assertFalse(processQestionsAndSum("arrb6????4xxbl5???eee5"))
        assertFalse(processQestionsAndSum("arrb64xxbl5??eee5"))
    }

    fun processQestionsAndSum(input: String?): Boolean {
        val a = input
        if (a.isNullOrEmpty()) {
            return false
        } else {
            val arr = a.split("???")
            if (arr.size <= 1) {
                return false
            }
            for (i in 1 until arr.size) {
                try {
                    val sum =
                        Integer.parseInt(arr[i - 1].last().toString()) + Integer.parseInt(arr[i].first().toString())
                    if (sum == 10) {
                        return true
                    }
                } catch (e: Exception) {
                }
            }
        }
        return false
    }


}
