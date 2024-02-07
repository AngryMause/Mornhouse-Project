package com.example.mornhouseproject.di

import com.example.mornhouseproject.network.NumberService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://numbersapi.com/"

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun providesBaseUrl(): String = BASE_URL

    @Provides
    fun provideOKHTTPClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(15000, TimeUnit.MILLISECONDS).callTimeout(15000, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    fun providesRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    fun provideNumberService(retrofit: Retrofit): NumberService =
        retrofit.create(NumberService::class.java)
}

