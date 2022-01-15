package com.application.anufriev.data

import com.application.anufriev.data.DAO.FilmDao
import com.application.anufriev.data.Entity.Film
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): Observable<List<Film>> = filmDao.getCachedFilms()
}