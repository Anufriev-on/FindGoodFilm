package com.application.anufriev.data

import android.content.ContentValues
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.application.anufriev.data.DAO.FilmDao
import com.application.anufriev.db.DatabaseHelper
import com.application.anufriev.data.Entity.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<List<Film>> = filmDao.getCachedFilms()
}