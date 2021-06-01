package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NewsDataStore {

    fun getNews(cityName: String, pageNumber: Int): Single<NewsEntity>

    fun saveNews(cityName: String, news: NewsEntity): Completable

    fun clearNews(): Completable
}