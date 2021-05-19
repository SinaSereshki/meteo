package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.BuildConfig
import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.github.aimsio.meteo.data.mapper.NewsMapper
import io.github.aimsio.meteo.data.network.NewsApi
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val mapper: NewsMapper
):NewsRemote {
    override fun getNews(cityName: String, pageNumber: Int): Single<NewsEntity> {
        return newsApi.getNews(cityName, BuildConfig.NEWS_PAGE_SIZE, pageNumber, BuildConfig.WEATHER_API_KEY).map { mapper.mapToEntity(it) }
    }
}