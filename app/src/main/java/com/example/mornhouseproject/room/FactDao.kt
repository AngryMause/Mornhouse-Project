package com.example.mornhouseproject.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {
    @Query("SELECT * FROM number_fact")
    fun loadFact(): Flow<List<NumbersFactBDEntity>>

    @Query("SELECT * FROM number_fact WHERE id=:id")
    fun getNumberFactById(id: Long): NumbersFactBDEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToDao(listEntity: NumbersFactBDEntity)
}