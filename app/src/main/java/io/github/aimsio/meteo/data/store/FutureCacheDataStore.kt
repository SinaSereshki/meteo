package io.github.aimsio.meteo.data.store

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.repository.CurrentCache
import io.github.aimsio.meteo.data.repository.CurrentDataStore
import io.github.aimsio.meteo.data.repository.FutureCache
import io.github.aimsio.meteo.data.repository.FutureDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FutureCacheDataStore @Inject constructor(
    private val futureCache: FutureCache
): FutureDataStore {
    override fun getFutureWeather(cityName: String): Single<FutureEntity> {
        return futureCache.getFutureWeather(cityName)
    }

    override fun saveFutureWeather(cityName: String, futureWeather: FutureEntity): Completable {
        return futureCache.saveFutureWeather(cityName, futureWeather = futureWeather)
    }

    override fun clearFutureWeather(): Completable {
        return futureCache.clearFutureWeather()
    }
}