package io.github.aimsio.meteo.data.cache.db

import io.github.aimsio.meteo.data.cache.db.database.AppDatabase
import io.github.aimsio.meteo.data.cache.db.mapper.NewsCachedMapper
import io.github.aimsio.meteo.data.cache.db.model.NewsCacheInfo
import io.github.aimsio.meteo.data.cache.db.model.NewsCached
import io.github.aimsio.meteo.data.cache.db.util.CACHE_EXPIRATION_TIME_MILLISECONDS
import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.github.aimsio.meteo.data.repository.NewsCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class NewsCacheImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    private val mapper: NewsCachedMapper
) : NewsCache {
    override fun clearNews(): Completable {
        return Completable.defer {
            appDatabase.newsCacheDao().deleteNews()
            Completable.complete()
        }
    }

    override fun saveNews(cityName: String, news: NewsEntity): Completable {
        return Completable.defer {
            appDatabase.newsCacheDao()
                .replaceNews(
                    NewsCached(
                        id = 0,
                        cityName = cityName,
                        articles = news.articles,
                        status = news.status,
                        totalResults = news.totalResults
                    )
                )
            Completable.complete()
        }
    }

    override fun getNews(cityName: String, pageNumber: Int): Single<NewsEntity> {
        return appDatabase.newsCacheDao().getNews(cityName).firstOrError()
            .map { mapper.mapFromCached(it) }

    }

    override fun isNewsCached(cityName: String): Single<Boolean> {
        return Single.zip(
            appDatabase.newsCachedInfoDao().getNewsCacheInfo(cityName).toSingle(
                NewsCacheInfo(lastUpdateTime = 0, city = cityName)
            ).map { it.city.equals(cityName, ignoreCase = true) },
            appDatabase.newsCacheDao().getNews(cityName).isEmpty.map {
                !it
            },
            { isCorrectQuery, isNewsCached ->
                Pair(isCorrectQuery, isNewsCached)
            }
        ).flatMap {
            Single.just(it.first && it.second)
        }
    }

    override fun setLastNewsCachedInfo(lastUpdateTime: Long, cityName: String): Completable {
        return Completable.defer {
            appDatabase.newsCachedInfoDao().replaceNewsCacheInfo(
                NewsCacheInfo(lastUpdateTime = lastUpdateTime, city = cityName)
            )
            Completable.complete()
        }
    }

    override fun isNewsCacheExpired(cityName: String): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (CACHE_EXPIRATION_TIME_MILLISECONDS).toLong()
        return appDatabase.newsCachedInfoDao().getNewsCacheInfo(cityName).toSingle(
            NewsCacheInfo(lastUpdateTime = 0, city = cityName)
        ).map {
            currentTime - it.lastUpdateTime > expirationTime
        }
    }
}