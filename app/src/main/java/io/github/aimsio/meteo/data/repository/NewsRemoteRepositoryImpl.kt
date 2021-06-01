package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(
    private val news: NewsRemote
): NewsRepository {
    override fun getNews(cityName: String, pageNumber: Int): Single<NewsEntity> {
        return news.getNews(cityName, pageNumber)
    }
}