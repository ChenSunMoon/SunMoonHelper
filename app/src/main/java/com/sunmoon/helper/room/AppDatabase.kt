package com.sunmoon.helper.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [RemindDao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): RemindDao
}