package io.github.aimsio.meteo.data.entity.model

import com.google.gson.annotations.SerializedName
import io.github.aimsio.meteo.data.model.weather.*

data class CurrentEntity(
    val id: Long,
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)