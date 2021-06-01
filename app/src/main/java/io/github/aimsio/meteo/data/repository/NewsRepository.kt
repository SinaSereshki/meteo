package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.reactivex.Single

interface NewsRepository {
    fun getNews(cityName:String, pageNumber: Int): Single<NewsEntity>
}
