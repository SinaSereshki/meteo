package io.github.aimsio.meteo.di.data

import dagger.Binds
import dagger.Module
import io.github.aimsio.meteo.data.repository.CurrentRemoteRepositoryImpl
import io.github.aimsio.meteo.data.repository.CurrentRepository

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun getCurrentWeatherRepository(implementation: CurrentRemoteRepositoryImpl): CurrentRepository

}