package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NewsCache {
    fun clearNews(): Completable
    fun saveNews(cityName: String, news: NewsEntity): Completable
    fun getNews(cityName: String, pageNumber: Int): Single<NewsEntity>
    fun isNewsCached(cityName: String): Single<Boolean>
    fun setLastNewsCachedInfo(lastUpdateTime:Long, cityName: String): Completable
    fun isNewsCacheExpired(cityName: String): Single<Boolean>
}