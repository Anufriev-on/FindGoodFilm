package com.application.anufriev.domain

//import android.telecom.Call

import androidx.lifecycle.LiveData
import com.application.anufriev.data.*
import com.application.anufriev.data.Entity.Film
import com.application.anufriev.data.Entity.TmdbResults
import com.application.anufriev.data.PreferenceProvider
//import com.application.anufriev.data.MainRepository
//import com.application.anufriev.data.TmdbApi
import com.application.anufriev.utils.Converter
import com.application.anufriev.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
//import okhttp3.Response
//import javax.security.auth.callback.Callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {

  //  val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
  var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()





    fun getFilmsFromApi(page: Int) {
        //Показываем ProgressBar

            progressBarState.onNext(true)

        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                //При успехе мы вызываем метод, передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                //Кладем фильмы в бд
                //В случае успешного ответа кладем фильмы в БД и выключаем ProgressBar
                Completable.fromSingle<List<Film>> {
                    repo.putToDb(list)
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
                progressBarState.onNext(false)
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала выключаем ProgressBar
                progressBarState.onNext(false)
            }
        })
    }


    fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDB()



    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}