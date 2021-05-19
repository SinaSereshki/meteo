package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FutureDataStore {

    fun getFutureWeather(cityName: String): Single<FutureEntity>

    fun saveFutureWeather(cityName: String, futureWeather: FutureEntity): Completable

    fun clearFutureWeather(): Completable
}