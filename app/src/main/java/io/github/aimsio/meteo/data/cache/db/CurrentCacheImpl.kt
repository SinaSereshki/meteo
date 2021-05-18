package io.github.aimsio.meteo.data.cache.db

import io.github.aimsio.meteo.data.cache.db.database.AppDatabase
import io.github.aimsio.meteo.data.cache.db.mapper.CurrentCachedMapper
import io.github.aimsio.meteo.data.cache.db.model.CurrentCacheInfo
import io.github.aimsio.meteo.data.cache.db.model.CurrentCached
import io.github.aimsio.meteo.data.cache.db.util.CACHE_EXPIRATION_TIME_MILLISECONDS
import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.repository.CurrentCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CurrentCacheImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val currentCachedMapper: CurrentCachedMapper
):CurrentCache {
    override fun clearCurrentWeather(): Completable {
        return Completable.defer {
            appDatabase.currentCacheDao().deleteCurrentWeather()
            Completable.complete()
        }
    }

    override fun saveCurrentWeather(cityName: String, currentWeather: CurrentEntity): Completable {
        return Completable.defer {
            appDatabase.currentCacheDao()
                .replaceCurrentWeather(
                    CurrentCached(
                        id = 0,
                        city = cityName,
                        base = currentWeather.base,
                        clouds = currentWeather.clouds,
                        cod = currentWeather.cod,
                        coord = currentWeather.coord,
                        dt = currentWeather.dt,
                        main = currentWeather.main,
                        name = currentWeather.name,
                        sys = currentWeather.sys,
                        timezone = currentWeather.timezone,
                        visibility = currentWeather.visibility,
                        weather = currentWeather.weather,
                        wind = currentWeather.wind
                    )
                )
            Completable.complete()
        }
    }

    override fun getCurrentWeather(cityName: String): Single<CurrentEntity> {
        return appDatabase.currentCacheDao().getCurrentWeather(cityName).firstOrError().map { currentCachedMapper.mapFromCached(it) }

    }

    override fun isCurrentWeatherCached(cityName: String): Single<Boolean> {
        return Single.zip(
            appDatabase.currentCachedInfoDao().getCurrentCachedInfo(cityName).toSingle(
                CurrentCacheInfo(lastUpdateTime = 0, city = cityName)
            ).map { it.city.equals(cityName, ignoreCase = true) },
            appDatabase.currentCacheDao().getCurrentWeather(cityName).isEmpty.map {
                !it
            },
            { isCorrectQuery, areAnyPhotosCached ->
                Pair(isCorrectQuery, areAnyPhotosCached)
            }
        ).flatMap {
            Single.just(it.first && it.second)
        }
    }

    override fun setLastCurrentCachedInfo(lastUpdateTime: Long, cityName: String): Completable {
        return Completable.defer {
            appDatabase.currentCachedInfoDao().replaceCurrentCachedInfo(
                CurrentCacheInfo(lastUpdateTime = lastUpdateTime, city = cityName)
            )
            Completable.complete()
        }
    }

    override fun isCurrentWeatherCacheExpired(cityName: String): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (CACHE_EXPIRATION_TIME_MILLISECONDS).toLong()
        return appDatabase.currentCachedInfoDao().getCurrentCachedInfo(cityName).toSingle(
            CurrentCacheInfo(lastUpdateTime = 0, city = cityName)
        ).map {
            currentTime - it.lastUpdateTime > expirationTime
        }
    }
}