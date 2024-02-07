package com.example.mornhouseproject.network

import com.example.mornhouseproject.model.NumberFactModel
import kotlinx.coroutines.flow.Flow

interface MaineRepository {
    suspend fun getNumberFact(int: Int)

    suspend fun getRandomFact()

    fun getFactList(): Flow<List<NumberFactModel>>
}