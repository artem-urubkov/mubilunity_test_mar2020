package com.auru.mobilunity.injection

import com.auru.mobilunity.TestCoroutineContextProvider
import com.auru.mobilunity.network.RetrofitRestService
import com.auru.mobilunity.utils.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModuleTest(
    private val baseUrl: String
): DataModule(baseUrl = baseUrl) {

    @Provides
    @Singleton
    override fun provideRetrofitRestService(retrofit: Retrofit): RetrofitRestService =
        Mockito.mock(RetrofitRestService::class.java)

    @Provides
    @Singleton
    override fun provideCoroutineContextProvider(): CoroutineContextProvider =
        TestCoroutineContextProvider()

}
