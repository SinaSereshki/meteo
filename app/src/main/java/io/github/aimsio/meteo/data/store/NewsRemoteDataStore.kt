package io.github.aimsio.meteo.data.store

import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.github.aimsio.meteo.data.repository.NewsDataStore
import io.github.aimsio.meteo.data.repository.NewsRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteDataStore @Inject constructor(
    private val newsRemote: NewsRemote
): NewsDataStore {
    override fun getNews(cityName: String): Single<NewsEntity> {
        return newsRemote.getNews(cityName)
    }

    override fun saveNews(cityName: String, news: NewsEntity): Completable {
        throw UnsupportedOperationException("Saving news isn't supported here...")
    }

    override fun clearNews(): Completable {
        throw UnsupportedOperationException("Saving news isn't supported here...")
    }
}