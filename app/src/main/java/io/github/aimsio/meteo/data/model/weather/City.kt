package io.github.aimsio.meteo.data.model.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CITY_ID = 0

@Entity(tableName = "city")
data class City(
    val coord: Coord,
    val country: String,
    @PrimaryKey
    val id: Int = CITY_ID,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)