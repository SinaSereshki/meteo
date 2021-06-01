package io.github.aimsio.meteo.data.entity.model

import io.github.aimsio.meteo.data.model.news.Article

data class NewsEntity(
    val id: Long,
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)