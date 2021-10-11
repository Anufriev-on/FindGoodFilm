package com.application.anufriev.domain

//import android.telecom.Call
import com.application.anufriev.data.*
import com.application.anufriev.data.Entity.TmdbResultsDto
//import com.application.anufriev.data.MainRepository
//import com.application.anufriev.data.TmdbApi
import com.application.anufriev.utils.Converter
import com.application.anufriev.viewmodel.HomeFragmentViewModel
//import okhttp3.Response
//import javax.security.auth.callback.Callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi) {
    //В конструктор мы будем передавать коллбэк из вью модели, чтобы реагировать на то, когда фильмы будут получены
    //и страницу, которую нужно загрузить (это для пагинации)
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
}
