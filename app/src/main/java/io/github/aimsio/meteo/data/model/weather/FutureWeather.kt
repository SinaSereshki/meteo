package io.github.aimsio.meteo.data.model.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FUTURE_WEATHER_ID = 0

@Entity(tableName = "future_weather")
data class FutureWeather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Info>,
    val message: Int,
    @PrimaryKey
    val id: Int = FUTURE_WEATHER_ID
)

