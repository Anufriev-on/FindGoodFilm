package com.application.anufriev.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.anufriev.App
import com.application.anufriev.data.Entity.Film
import com.application.anufriev.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {


    //Инициализируем интерактор
    @Inject
    lateinit var interactor: Interactor
    val showProgressBar: BehaviorSubject<Boolean>
    val filmsListLiveData: Observable<List<Film>>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1)
    }


    fun getSearchResult(search: String) = interactor.getSearchResultFromApi(search)
}