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
//import okhttp3.Response
//import javax.security.auth.callback.Callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                //При успехе мы вызываем метод, передаем onSuccess и в этот коллбэк список фильмов
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                //Кладем фильмы в бд
                list.forEach {
                    repo.putToDb(list)
                }
                callback.onSuccess()
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }


    fun getFilmsFromDB(): LiveData<List<Film>> = repo.getAllFromDB()



    //Метод для сохранения настроек
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    //Метод для получения настроек
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}