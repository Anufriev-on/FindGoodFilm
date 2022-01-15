package com.application.anufriev.domain

//import android.telecom.Call

import com.application.anufriev.data.API
import com.application.anufriev.data.*
import com.application.anufriev.data.Entity.Film
import com.application.anufriev.data.PreferenceProvider
import com.application.anufriev.utils.Converter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import remote_module.TmdbApi


class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {

  //  val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
  var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()





    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar
        progressBarState.onNext(true)
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page)
            .subscribeOn(Schedulers.io())
            .map {
                Converter.convertApiListToDtoList(it.tmdbFilms)
            }
            .subscribeBy(
                onError = {
                    progressBarState.onNext(false)
                },
                onNext = {
                    progressBarState.onNext(false)
                    repo.putToDb(it)
                }
            )
    }


    fun getSearchResultFromApi(search: String): Observable<List<Film>> = retrofitService.getFilmFromSearch(API.KEY, "ru-RU", search, 1)
        .map {
            Converter.convertApiListToDtoList(it.tmdbFilms)
        }





    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()


    fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDB()


}