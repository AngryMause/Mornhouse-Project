package com.example.mornhouseproject.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumbersFactBDEntity::class], version = 1, exportSchema = false)
abstract class FactDataBase : RoomDatabase() {
    abstract fun myDao(): FactDao
}