package com.application.anufriev.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.anufriev.App
import com.application.anufriev.domain.Film
import com.application.anufriev.domain.Interactor
import okhttp3.internal.Internal.instance

import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData:  MutableLiveData<List<Film>> = MutableLiveData()
    //Инициализируем интерактор
  //  private val interactor:  Interactor by inject()

    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor


    init {
        App.instance.dagger.inject(this)
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }

            override fun onFailure() {
                filmsListLiveData.postValue(interactor.getFilmsFromDB())
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}