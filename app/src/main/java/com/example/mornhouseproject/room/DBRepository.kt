package com.example.mornhouseproject.room

import com.example.mornhouseproject.di.IoDispatcher
import com.example.mornhouseproject.model.NumberFactModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DBRepository @Inject constructor(
    private val factDao: FactDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    fun listFact(): Flow<List<NumbersFactBDEntity>> {
        return factDao.loadFact().flowOn(dispatcher)
    }

    suspend fun getFactByID(id: Long): NumberFactModel {
        return withContext(dispatcher) {
            factDao.getNumberFactById(id).toNumberFactModel()
        }
    }

    suspend fun addFactToDB(numbersFactBDEntity: NumbersFactBDEntity) {
        withContext(dispatcher) {
            factDao.addToDao(numbersFactBDEntity)
        }
    }
}