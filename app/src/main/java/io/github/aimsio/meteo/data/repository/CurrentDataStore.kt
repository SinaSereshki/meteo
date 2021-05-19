package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.reactivex.Completable
import io.reactivex.Single

interface CurrentDataStore {

    fun getCurrentWeather(cityName: String): Single<CurrentEntity>

    fun saveCurrentWeather(cityName: String, currentWeather: CurrentEntity): Completable

    fun clearCurrentWeather(): Completable
}