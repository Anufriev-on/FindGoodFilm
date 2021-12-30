package com.application.anufriev.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.anufriev.data.DAO.FilmDao
import com.application.anufriev.data.Entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}