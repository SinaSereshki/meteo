package io.github.aimsio.meteo.data.cache.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHED
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHED_ID
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHED_TABLE
import io.github.aimsio.meteo.data.model.weather.*

@Entity(tableName = CURRENT_CACHED_TABLE)
data class CurrentCached(

    @PrimaryKey
    @ColumnInfo(name = CURRENT_CACHED_ID)
    var id: Long,
    @ColumnInfo(name = CURRENT_CACHED)
    var city: String,
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