package com.example.mornhouseproject.network

import com.example.mornhouseproject.di.IoDispatcher
import com.example.mornhouseproject.model.NumberFactModel
import com.example.mornhouseproject.room.DBRepository
import com.example.mornhouseproject.room.NumbersFactBDEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class MaineRepositoryImpl @Inject constructor(
    private val remoteData: NumberService,
    private val dbRepository: DBRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : MaineRepository {
    override suspend fun getNumberFact(int: Int) {
        saveNumberFactToDB(NumbersFactBDEntity.fromApiModel(remoteData.getNumberFact(int)))
    }

    override suspend fun getRandomFact() {
        saveNumberFactToDB(NumbersFactBDEntity.fromApiModel(remoteData.getRandomFact()))
    }

    override fun getFactList(): Flow<List<NumberFactModel>> {
        return dbRepository.listFact().map { list ->
            list.map {
                it.toNumberFactModel()
            }
        }
    }

    private suspend fun saveNumberFactToDB(numbersFactBDEntity: NumbersFactBDEntity) {
        withContext(dispatcher) {
            dbRepository.addFactToDB(numbersFactBDEntity)
        }
    }
}
