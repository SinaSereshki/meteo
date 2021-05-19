package io.github.aimsio.meteo.domain.repository

import io.github.aimsio.meteo.data.model.news.News
import io.reactivex.Single

interface NewsDomainRepository {
    fun getNews(cityName:String): Single<News>
}