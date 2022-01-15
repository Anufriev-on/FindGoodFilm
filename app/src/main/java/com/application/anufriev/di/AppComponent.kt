package com.application.anufriev.di

import com.application.anufriev.di.modules.DatabaseModule
import com.application.anufriev.di.modules.DomainModule
import com.application.anufriev.viewmodel.HomeFragmentViewModel
import com.application.anufriev.viewmodel.SettingsFragmentViewModel
import remote_module.RemoteProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    dependencies = [RemoteProvider::class],
    modules = [
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась возможность внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    //метод для того, чтобы появилась возможность внедрять зависимости в SettingsFragmentViewModel
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}