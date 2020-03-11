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

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.auru.mobilunity.dto.RepoElement
import com.auru.mobilunity.injection.*
import com.auru.mobilunity.network.RetrofitRestService
import com.auru.mobilunity.ui.main.MainViewModel
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.assertEquals
import org.mockito.Mockito
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainViewModelTest {

    // Subject under test
    private lateinit var mainViewModel: MainViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setupViewModel() {
        val testApplication = ApplicationProvider.getApplicationContext() as AndroidApp
        testApplication.component = DaggerAppComponent.builder()
            .dataModule(DataModuleTest(baseUrl))
            .build()
        mainViewModel = MainViewModel(testApplication)
    }


    @Test
    fun refreshRepoData_positive_test() {
        runBlocking {
            val mockApi = Mockito.mock(RetrofitRestService::class.java)
            mainViewModel.restApi = mockApi
            Mockito.`when`(mockApi.getRepoElements()).thenReturn(
                Single.just(
                    arrayOf(
                        RepoElementsParsingUnitTest.expectedElement1,
                        RepoElementsParsingUnitTest.expectedElement2
                    )
                )
            )

            // When adding a new task
            mainViewModel.refreshRepoData()

            // Then the new task event is triggered
            val value = mainViewModel.getRepoElementsLD().getOrAwaitValue()

            assertEquals(value[0], RepoElementsParsingUnitTest.expectedElement1)
            assertEquals(value[1], RepoElementsParsingUnitTest.expectedElement2)
        }
    }


//    @Test
//    fun addNewTask_setsNewTaskEvent() {
//        // When adding a new task
//        mainViewModel.addNewTask()
//
//        // Then the new task event is triggered
//        val value = mainViewModel.newTaskEvent.getOrAwaitValue()
//
//        assertThat(value.getContentIfNotHandled(), not(nullValue()))
//
//
//    }
//
//    @Test
//    fun setFilterAllTasks_tasksAddViewVisible() {
//        // When the filter type is ALL_TASKS
//        mainViewModel.setFiltering(TasksFilterType.ALL_TASKS)
//
//        // Then the "Add task" action is visible
//        assertThat(mainViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
//    }

}
