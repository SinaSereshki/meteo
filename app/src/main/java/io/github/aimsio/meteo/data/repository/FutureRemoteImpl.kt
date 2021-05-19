package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.BuildConfig
import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.mapper.FutureMapper
import io.github.aimsio.meteo.data.network.WeatherApi
import io.reactivex.Single
import javax.inject.Inject

class FutureRemoteImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val mapper: FutureMapper
):FutureRemote {
    override fun getFutureWeather(cityName: String): Single<FutureEntity> {
        return weatherApi.getFutureWeather(cityName, BuildConfig.WEATHER_FUTURE_FORECAST_DAYS , BuildConfig.WEATHER_API_KEY).map { mapper.mapToEntity(it) }
    }
}