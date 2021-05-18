package io.github.aimsio.meteo.data.cache.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHE_INFO_TABLE

@Entity(tableName = CURRENT_CACHE_INFO_TABLE)
data class CurrentCacheInfo(

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1,
    var lastUpdateTime: Long,
    var city:String

)
