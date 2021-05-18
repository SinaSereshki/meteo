package io.github.aimsio.meteo.data.cache.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.aimsio.meteo.data.cache.db.util.*
import io.github.aimsio.meteo.data.model.news.Article
import io.github.aimsio.meteo.data.model.weather.Info

@Entity(tableName = NEWS_CACHED_TABLE)
data class NewsCached(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = NEWS_CACHED_ID)
    var id: Long,
    @ColumnInfo(name = NEWS_CACHED)
    var cityName: String,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int

)