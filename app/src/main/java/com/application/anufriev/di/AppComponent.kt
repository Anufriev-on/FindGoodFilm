package com.application.anufriev.di

import com.application.anufriev.di.modules.DatabaseModule
import com.application.anufriev.di.modules.DomainModule
import com.application.anufriev.di.modules.RemoteModule
import com.application.anufriev.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}