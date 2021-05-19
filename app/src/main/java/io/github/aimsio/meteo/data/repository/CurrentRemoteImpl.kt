package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.BuildConfig
import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.mapper.CurrentMapper
import io.github.aimsio.meteo.data.network.WeatherApi
import io.reactivex.Single
import javax.inject.Inject

class CurrentRemoteImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val mapper: CurrentMapper
):CurrentRemote {
    override fun getCurrentWeather(cityName: String): Single<CurrentEntity> {
        return weatherApi.getCurrentWeather(cityName, BuildConfig.WEATHER_API_KEY).map { mapper.mapToEntity(it) }
    }
}