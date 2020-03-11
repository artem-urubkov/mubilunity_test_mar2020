package com.auru.mobilunity.injection

import com.auru.mobilunity.network.RetrofitRestService
import com.auru.mobilunity.utils.CoroutineContextProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class DataModule(
    private val baseUrl: String
) {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()
        return gson
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    open fun provideRetrofitClient(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    open fun provideRetrofitRestService(retrofit: Retrofit): RetrofitRestService =
        retrofit.create(RetrofitRestService::class.java)

    @Provides
    fun provideErrorConverter(retrofit: Retrofit): Converter<ResponseBody, Error> =
        retrofit.responseBodyConverter(Error::class.java, arrayOfNulls(0))

    @Provides
    open fun provideCoroutineContextProvider(): CoroutineContextProvider =
        CoroutineContextProvider()


}
