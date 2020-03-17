package com.auru.mobilunity.injection

import com.auru.mobilunity.network.RetrofitRestService
import com.auru.mobilunity.utils.CoroutineContextProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
    //TODO Gson is thread safe -> so, if have >1 threads for serialize/deserialize stuff, investigate it's instantiation optimization
    fun provideGson(): Gson =
        GsonBuilder()
            .serializeNulls()
            .create()

    @Provides
    @Singleton
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
    @Singleton
    open fun provideRetrofitRestService(retrofit: Retrofit): RetrofitRestService =
        retrofit.create(RetrofitRestService::class.java)

    @Provides
    @Singleton
    open fun provideCoroutineContextProvider(): CoroutineContextProvider =
        CoroutineContextProvider()


}
