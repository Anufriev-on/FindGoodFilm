package com.application.anufriev.di.modules

import android.content.Context
import androidx.room.Room
import com.application.anufriev.data.DAO.FilmDao
import com.application.anufriev.data.MainRepository
import com.application.anufriev.db.AppDatabase
import com.application.anufriev.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}