package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.model.weather.FutureWeather
import io.reactivex.Completable
import io.reactivex.Single

interface FutureCache {
    fun clearFutureWeather(): Completable
    fun saveFutureWeather(cityName: String, futureWeather: FutureEntity): Completable
    fun getFutureWeather(cityName: String): Single<FutureEntity>
    fun isFutureWeatherCached(cityName: String): Single<Boolean>
    fun setLastFutureCachedInfo(lastUpdateTime:Long, cityName: String): Completable
    fun isFutureWeatherCacheExpired(cityName: String): Single<Boolean>
}