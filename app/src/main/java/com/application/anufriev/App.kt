package com.application.anufriev

import android.app.Application
import com.application.anufriev.di.AppComponent
import com.application.anufriev.di.DaggerAppComponent


class App : Application() {
    lateinit var dagger: AppComponent



    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()


    }

    companion object {
        lateinit var instance: App
            private set
    }
}


