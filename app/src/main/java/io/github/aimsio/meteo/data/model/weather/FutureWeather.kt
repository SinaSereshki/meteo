package io.github.aimsio.meteo.data.model.weather

data class FutureWeather(
    val id: Long,
    val city: String,
    val cnt: Int,
    val cod: String,
    val list: List<Info>,
    val message: Int
)

