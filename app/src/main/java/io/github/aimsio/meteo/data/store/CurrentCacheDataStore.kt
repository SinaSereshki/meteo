package io.github.aimsio.meteo.data.store

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.repository.CurrentCache
import io.github.aimsio.meteo.data.repository.CurrentDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CurrentCacheDataStore @Inject constructor(
    private val currentCache:CurrentCache
):CurrentDataStore {
    override fun getCurrentWeather(cityName: String): Single<CurrentEntity> {
        return currentCache.getCurrentWeather(cityName)
    }

    override fun saveCurrentWeather(cityName: String, currentWeather: CurrentEntity): Completable {
        return currentCache.saveCurrentWeather(cityName, currentWeather = currentWeather)
    }

    override fun clearCurrentWeather(): Completable {
        return currentCache.clearCurrentWeather()
    }
}