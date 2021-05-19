package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.mapper.CurrentMapper
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.store.CurrentCacheDataStore
import io.github.aimsio.meteo.data.store.CurrentRemoteDataStore
import io.github.aimsio.meteo.domain.repository.CurrentWeatherDomainRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class CurrentDataRepository @Inject constructor(
    private val mapper: CurrentMapper,
    private val currentRemoteDataStore:CurrentRemoteDataStore,
    private val currentCacheDataStore: CurrentCacheDataStore,
    private val cache: CurrentCache
): CurrentWeatherDomainRepository {
    override fun getCurrentWeather(cityName: String): Single<CurrentWeather> {
        val response:Single<CurrentWeather>

        response = currentRemoteDataStore.getCurrentWeather(cityName).flatMap { photoEntities ->
            Single.zip(
                cache.isCurrentWeatherCached(cityName),
                cache.isCurrentWeatherCacheExpired(cityName),
                { areCached, isExpired ->
                    Pair(areCached, isExpired)
                }
            ).map {
                if(it.first && it.second)
                    currentCacheDataStore.clearCurrentWeather()
            }
            currentCacheDataStore.saveCurrentWeather(cityName, photoEntities)
                .andThen(Single.just(photoEntities))
        }.map { photoEntity ->
            mapper.mapFromEntity(photoEntity)
        }
        return response
    }
}