package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.mapper.NewsMapper
import io.github.aimsio.meteo.data.model.news.News
import io.github.aimsio.meteo.data.store.NewsCacheDataStore
import io.github.aimsio.meteo.data.store.NewsRemoteDataStore
import io.github.aimsio.meteo.domain.repository.NewsDomainRepository
import io.reactivex.Single
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val mapper: NewsMapper,
    private val newsRemoteDataStore: NewsRemoteDataStore,
    private val newsCacheDataStore: NewsCacheDataStore,
    private val cache: NewsCache
): NewsDomainRepository {
    override fun getNews(cityName: String, pageNumber: Int): Single<News> {
        val response:Single<News>

        response = newsRemoteDataStore.getNews(cityName, pageNumber).flatMap { photoEntities ->
            Single.zip(
                cache.isNewsCached(cityName),
                cache.isNewsCacheExpired(cityName),
                { areCached, isExpired ->
                    Pair(areCached, isExpired)
                }
            ).map {
                if(it.first && it.second)
                    newsCacheDataStore.clearNews()
            }
            newsCacheDataStore.saveNews(cityName, photoEntities)
                .andThen(Single.just(photoEntities))
        }.map { photoEntity ->
            mapper.mapFromEntity(photoEntity)
        }
        return response
    }
}