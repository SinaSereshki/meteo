package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.mapper.CurrentMapper
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.store.CurrentCacheDataStore
import io.github.aimsio.meteo.domain.executor.PostExecutionThread
import io.github.aimsio.meteo.domain.executor.ThreadExecutor
import io.github.aimsio.meteo.domain.repository.CurrentWeatherDomainRepository
import io.reactivex.Single
import javax.inject.Inject

class CurrentCheckRepository @Inject constructor(
    private val currentCacheDataStore: CurrentCacheDataStore,
    private val currentWeatherDomainRepository: CurrentWeatherDomainRepository,
    private val mapper: CurrentMapper

) {

    fun observeCurrent(
        cityName:String,
        isCachedAndNotExpired:Boolean
    ): Single<CurrentWeather> {
        return if(isCachedAndNotExpired)
            observeLocalCurrentWeather(cityName)
        else
            observeRemoteCurrentWeather(cityName)
    }



    private fun observeLocalCurrentWeather(cityName: String): Single<CurrentWeather> {
        return currentCacheDataStore.getCurrentWeather(cityName).map { mapper.mapFromEntity(it) }
    }

    private fun observeRemoteCurrentWeather(cityName: String)
            : Single<CurrentWeather> {
        return currentWeatherDomainRepository.getCurrentWeather(cityName)
    }
}