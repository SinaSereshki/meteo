package io.github.aimsio.meteo.data.network

import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.model.weather.FutureWeather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current/data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") key: String = "<YOUR_TOKEN>"
    ): Single<CurrentWeather>

    @GET("forecast/data/2.5/forecast")
    fun getFutureWeather(
        @Query("q") city: String,
        @Query("cnt") count: Int,
        @Query("appid") key: String = "<YOUR_TOKEN>"
    ): Single<FutureWeather>

}