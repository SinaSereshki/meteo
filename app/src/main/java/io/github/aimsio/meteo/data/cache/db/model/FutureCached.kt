package io.github.aimsio.meteo.data.cache.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.aimsio.meteo.data.cache.db.util.*
import io.github.aimsio.meteo.data.model.weather.*

@Entity(tableName = FUTURE_CACHED_TABLE)
data class FutureCached(

    @PrimaryKey
    @ColumnInfo(name = FUTURE_CACHED_ID)
    var id: Long,
    @ColumnInfo(name = FUTURE_CACHED)
    var cityName: String,
    val cnt: Int,
    val cod: String,
    val list: List<Info>,
    val message: Int

)