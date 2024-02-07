package com.example.mornhouseproject.network

import com.example.mornhouseproject.model.api.ApiModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NumberService {
    @Headers("Content-Type: application/json")
    @GET("{number}")
    suspend fun getNumberFact(@Path("number") number: Int): ApiModel

    @Headers("Content-Type: application/json")
    @GET("random/")
    suspend fun getRandomFact(): ApiModel
}