package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.mapper.FutureMapper
import io.github.aimsio.meteo.data.model.weather.FutureWeather
import io.github.aimsio.meteo.data.store.FutureCacheDataStore
import io.github.aimsio.meteo.data.store.FutureRemoteDataStore
import io.github.aimsio.meteo.domain.repository.FutureWeatherDomainRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class FutureDataRepository @Inject constructor(
    private val mapper: FutureMapper,
    private val futureRemoteDataStore:FutureRemoteDataStore,
    private val futureCacheDataStore: FutureCacheDataStore,
    private val cache: FutureCache
): FutureWeatherDomainRepository {
    override fun getFutureWeather(cityName: String): Single<FutureWeather> {
        val response:Single<FutureWeather>

        response = futureRemoteDataStore.getFutureWeather(cityName).flatMap { photoEntities ->
            Single.zip(
                cache.isFutureWeatherCached(cityName),
                cache.isFutureWeatherCacheExpired(cityName),
                { areCached, isExpired ->
                    Pair(areCached, isExpired)
                }
            ).map {
                if(it.first && it.second)
                    futureCacheDataStore.clearFutureWeather()
            }
            futureCacheDataStore.saveFutureWeather(cityName, photoEntities)
                .andThen(Single.just(photoEntities))
        }.map { photoEntity ->
            mapper.mapFromEntity(photoEntity)
        }
        return response
    }
}