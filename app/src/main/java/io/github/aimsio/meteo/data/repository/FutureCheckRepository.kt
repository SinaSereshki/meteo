package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.mapper.FutureMapper
import io.github.aimsio.meteo.data.model.weather.FutureWeather
import io.github.aimsio.meteo.data.store.FutureCacheDataStore
import io.github.aimsio.meteo.domain.repository.FutureWeatherDomainRepository
import io.reactivex.Single
import javax.inject.Inject

class FutureCheckRepository @Inject constructor(
    private val futureCacheDataStore: FutureCacheDataStore,
    private val futureWeatherDomainRepository: FutureWeatherDomainRepository,
    private val mapper: FutureMapper

) {

    fun observeFuture(
        cityName:String,
        isCachedAndNotExpired:Boolean
    ): Single<FutureWeather> {
        return if(isCachedAndNotExpired)
            observeLocalFutureWeather(cityName)
        else
            observeRemoteFutureWeather(cityName)
    }



    private fun observeLocalFutureWeather(cityName: String): Single<FutureWeather> {
        return futureCacheDataStore.getFutureWeather(cityName).map { mapper.mapFromEntity(it) }
    }

    private fun observeRemoteFutureWeather(cityName: String)
            : Single<FutureWeather> {
        return futureWeatherDomainRepository.getFutureWeather(cityName)
    }
}