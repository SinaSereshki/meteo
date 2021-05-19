package io.github.aimsio.meteo.data.store

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.github.aimsio.meteo.data.model.news.News
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.repository.CurrentCache
import io.github.aimsio.meteo.data.repository.CurrentDataStore
import io.github.aimsio.meteo.data.repository.NewsCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsCacheDataStore @Inject constructor(
    private val newsCache: NewsCache
):NewsDataStore {
    override fun getNews(cityName: String): Single<NewsEntity> {
        return newsCache.getCNews(cityName)
    }

    override fun saveNews(cityName: String, news: NewsEntity): Completable {
        return newsCache.saveNews(cityName, news = News)
    }

    override fun clearNews(): Completable {
        return newsCache.clearNews()
    }
}