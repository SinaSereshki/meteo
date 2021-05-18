package io.github.aimsio.meteo.data.cache.db

import io.github.aimsio.meteo.data.cache.db.database.AppDatabase
import io.github.aimsio.meteo.data.cache.db.mapper.FutureCachedMapper
import io.github.aimsio.meteo.data.cache.db.model.FutureCacheInfo
import io.github.aimsio.meteo.data.cache.db.model.FutureCached
import io.github.aimsio.meteo.data.cache.db.util.CACHE_EXPIRATION_TIME_MILLISECONDS
import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.repository.FutureCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FutureCacheImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val futureCachedMapper: FutureCachedMapper
) : FutureCache {
    override fun clearFutureWeather(): Completable {
        return Completable.defer {
            appDatabase.futureCacheDao().deleteFutureWeather()
            Completable.complete()
        }
    }

    override fun saveFutureWeather(cityName: String, future: FutureEntity): Completable {
        return Completable.defer {
            appDatabase.futureCacheDao()
                .replaceFutureWeather(
                    FutureCached(
                        id = 0,
                        cityName = cityName,
                        cnt = future.cnt,
                        cod = future.cod,
                        list = future.list,
                        message = future.message
                    )
                )
            Completable.complete()
        }
    }

    override fun getFutureWeather(cityName: String): Single<FutureEntity> {
        return appDatabase.futureCacheDao().getFutureWeather(cityName).firstOrError().map {
            futureCachedMapper.mapFromCached(
                FutureCached(it.id, it.cityName, it.cnt, it.cod, it.list, it.message)
            )
        }
    }

    override fun isFutureWeatherCached(cityName: String): Single<Boolean> {
        return Single.zip(
            appDatabase.futureCachedInfoDao().getFutureCacheInfo(cityName).toSingle(
                FutureCacheInfo(lastUpdateTime = 0, city = cityName)
            ).map { it.city.equals(cityName, ignoreCase = true) },
            appDatabase.futureCacheDao().getFutureWeather(cityName).isEmpty.map {
                !it
            },
            { isCorrectQuery, isFutureWeatherCached ->
                Pair(isCorrectQuery, isFutureWeatherCached)
            }
        ).flatMap {
            Single.just(it.first && it.second)
        }
    }

    override fun setLastFutureCachedInfo(lastUpdateTime: Long, cityName: String): Completable {
        return Completable.defer {
            appDatabase.futureCachedInfoDao().replaceFutureCacheInfo(
                FutureCacheInfo(lastUpdateTime = lastUpdateTime, city = cityName)
            )
            Completable.complete()
        }
    }

    override fun isFutureWeatherCacheExpired(cityName: String): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (CACHE_EXPIRATION_TIME_MILLISECONDS).toLong()
        return appDatabase.futureCachedInfoDao().getFutureCacheInfo(cityName).toSingle(
            FutureCacheInfo(lastUpdateTime = 0, city = cityName)
        ).map {
            currentTime - it.lastUpdateTime > expirationTime
        }
    }
}