package com.application.anufriev

import android.app.Application
import com.application.anufriev.di.AppComponent
import com.application.anufriev.di.DaggerAppComponent
import com.application.anufriev.di.modules.DatabaseModule
import com.application.anufriev.di.modules.DomainModule
import remote_module.DaggerRemoteComponent




class App : Application() {
    lateinit var dagger: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        val remoteProvider = DaggerRemoteComponent.create()
        dagger = DaggerAppComponent.builder()
            .remoteProvider(remoteProvider)
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }

}

