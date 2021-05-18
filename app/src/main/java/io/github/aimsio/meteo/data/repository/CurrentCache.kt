package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Single

interface CurrentCache {
    fun clearCurrentWeather(): Completable
    fun saveCurrentWeather(cityName: String, currentWeather: CurrentEntity): Completable
    fun getCurrentWeather(cityName: String): Single<CurrentEntity>
    fun isCurrentWeatherCached(cityName: String): Single<Boolean>
    fun setLastCurrentCachedInfo(lastUpdateTime:Long, cityName: String):Completable
    fun isCurrentWeatherCacheExpired(cityName: String):Single<Boolean>
}