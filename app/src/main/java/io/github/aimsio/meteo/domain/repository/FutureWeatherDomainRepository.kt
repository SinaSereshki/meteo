package io.github.aimsio.meteo.domain.repository

import io.github.aimsio.meteo.data.model.weather.FutureWeather
import io.reactivex.Single

interface FutureWeatherDomainRepository {
    fun getFutureWeather(cityName:String): Single<FutureWeather>
}