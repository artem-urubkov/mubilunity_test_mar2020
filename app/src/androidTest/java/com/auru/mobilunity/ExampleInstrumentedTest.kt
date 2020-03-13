package com.auru.mobilunity

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.injection.DaggerAppComponent
import com.auru.mobilunity.injection.InjectedInstancesHolder
import com.auru.mobilunity.network.RetrofitRestService
import com.auru.mobilunity.sharedData.DataModuleTest
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement1
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement2
import com.auru.mobilunity.ui.main.MainActivity
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import java.io.IOException


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var scenario: ActivityScenario<MainActivity>

    private lateinit var mockApi: RetrofitRestService

    private lateinit var context: Context


    @Before
    fun initTest() {
        val testApplication = ApplicationProvider.getApplicationContext() as AndroidApp
        testApplication.component = DaggerAppComponent.builder()
            .dataModule(DataModuleTest(baseUrl))
            .build()

        val injectedInstancesHolder = InjectedInstancesHolder(testApplication)
        mockApi = injectedInstancesHolder.restApi

        context = testApplication.applicationContext
    }

    @After
    fun cleanup() {
        scenario.close()
    }


    @Test
    fun test_positiveFlow_withEmptyResult() {
        Mockito.`when`(mockApi.getRepoElements()).thenReturn(
            Single.just(
                emptyArray<RepoElement>()
            )
        )

        scenario = ActivityScenario.launch(MainActivity::class.java)

        //TODO why the test doesn't need to wait here???
//        runBlocking {
//            delay(1000)
//        }

        onView(withId(R.id.empty_list_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun test_positiveFlow_withGoodResult() {
        Mockito.`when`(mockApi.getRepoElements()).thenReturn(
            Single.just(
                arrayOf(
                    expectedElement1,
                    expectedElement2
                )
            )
        )

        scenario = ActivityScenario.launch(MainActivity::class.java)

//        runBlocking {
//            delay(1000)
//        }

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withText(expectedElement2.name)).check(matches(isDisplayed()))
    }


    @Test
    fun test_negativeFlow() {
        BDDMockito.given(mockApi.getRepoElements()).willAnswer {
            throw IOException("Ooops")
        }

        scenario = ActivityScenario.launch(MainActivity::class.java)

//        runBlocking {
//            delay(1000)
//        }

        onView(withId(R.id.empty_list_tv)).check(matches(isDisplayed()))
        onView(withText(context.getString(R.string.error_inet_unavailable))).check(matches(isDisplayed()))
    }

}
