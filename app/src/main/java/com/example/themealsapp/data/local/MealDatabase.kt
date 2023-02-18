package com.example.themealsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themealsapp.data.local.entity.MealEntity

@Database(
    entities = [
        MealEntity::class
    ],
    version = 1
)
abstract class MealDatabase: RoomDatabase() {

    abstract val dao: MealDao
}