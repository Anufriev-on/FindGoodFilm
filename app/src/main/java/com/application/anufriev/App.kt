package com.application.anufriev

import android.app.Application
import com.application.anufriev.di.AppComponent
import com.application.anufriev.di.DaggerAppComponent
import com.application.anufriev.di.modules.DatabaseModule
import com.application.anufriev.di.modules.DomainModule
import com.application.anufriev.di.modules.RemoteModule
import okhttp3.internal.Internal.instance


class App : Application() {
    lateinit var dagger: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }

}

