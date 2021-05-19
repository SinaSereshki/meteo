package io.github.aimsio.meteo.domain.repository

import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.reactivex.Single

interface CurrentWeatherDomainRepository {

    fun getCurrentWeather(cityName:String):Single<CurrentWeather>
}