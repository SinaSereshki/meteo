package io.github.aimsio.meteo.di.data

import dagger.Binds
import dagger.Module
import io.github.aimsio.meteo.data.repository.CurrentDataRepository
import io.github.aimsio.meteo.domain.repository.CurrentWeatherDomainRepository


@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: CurrentDataRepository): CurrentWeatherDomainRepository

}