package com.application.anufriev.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.anufriev.App
import com.application.anufriev.data.Entity.Film
import com.application.anufriev.domain.Interactor
import java.util.concurrent.Executors

import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
 //   val filmsListLiveData:  MutableLiveData<List<Film>> = MutableLiveData()
    //Инициализируем интерактор
  //  private val interactor:  Interactor by inject()

    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()


    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                showProgressBar.postValue(false)
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}