package com.application.anufriev.di.modules

import com.application.anufriev.data.MainRepository
import com.application.anufriev.data.TmdbApi
import com.application.anufriev.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}