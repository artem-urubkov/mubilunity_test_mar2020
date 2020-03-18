/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.auru.mobilunity

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.injection.DaggerAppComponent
import com.auru.mobilunity.network.RetrofitRestService
import com.auru.mobilunity.network.baseUrl
import com.auru.mobilunity.presentation.controllers.mainscreen.MainViewModel
import com.auru.mobilunity.presentation.viewutils.LCEResult
import com.auru.mobilunity.sharedData.DataModuleTest
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement1
import com.auru.mobilunity.sharedData.RepoElementsTestData.Companion.expectedElement2
import com.auru.mobilunity.utils.MainCoroutineScopeRule
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.robolectric.annotation.Config
import java.io.IOException


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainViewModelTest {

    // Subject under test
    private lateinit var mainViewModel: MainViewModel

    private lateinit var mockApi: RetrofitRestService

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()


    @Before
    fun setupViewModel() {
        val testApplication = ApplicationProvider.getApplicationContext() as AndroidApp
        testApplication.component = DaggerAppComponent.builder()
            .dataModule(DataModuleTest(baseUrl))
            .build()
        mainViewModel =
            MainViewModel(
                testApplication
            )

        mockApi = mainViewModel.restApi
    }


    @Test
    fun refreshRepoData_positive_test() {
        coroutineScope.runBlockingTest {

            Mockito.`when`(mockApi.getRepoElements()).thenReturn(
                arrayOf(
                    expectedElement1,
                    expectedElement2
                )
            )

            // When adding a new task
            mainViewModel.refreshRepoData()

            // Then the new task event is triggered
            val value = mainViewModel.getRepoElementsResultLD().getOrAwaitValue()

            val data = ((value as LCEResult.Success).data as List<RepoElement>)
            assertEquals(data[0], expectedElement1)
            assertEquals(data[1], expectedElement2)
        }
    }


    @Test
    fun refreshRepoData_negative_test() {
        coroutineScope.runBlockingTest {
            given(mockApi.getRepoElements()).willAnswer {
                throw IOException("Ooops")
            }

            mainViewModel.refreshRepoData()
            val value = mainViewModel.getRepoElementsResultLD().getOrAwaitValue()
            val result = (value as LCEResult.Failure).errorMessage
            assertNotNull(result)
        }
    }

}
