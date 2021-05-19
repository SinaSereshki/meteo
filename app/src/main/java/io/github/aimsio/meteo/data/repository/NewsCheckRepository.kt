package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.mapper.NewsMapper
import io.github.aimsio.meteo.data.model.news.News
import io.github.aimsio.meteo.data.store.NewsCacheDataStore
import io.github.aimsio.meteo.domain.repository.NewsDomainRepository
import io.reactivex.Single
import javax.inject.Inject

class NewsCheckRepository @Inject constructor(
    private val newsCacheDataStore: NewsCacheDataStore,
    private val newsDomainRepository: NewsDomainRepository,
    private val mapper: NewsMapper

) {

    fun observeNews(
        cityName:String,
        isCachedAndNotExpired:Boolean
    ): Single<News> {
        return if(isCachedAndNotExpired)
            observeLocalNews(cityName)
        else
            observeRemoteNews(cityName)
    }



    private fun observeLocalNews(cityName: String): Single<News> {
        return newsCacheDataStore.getNews(cityName).map { mapper.mapFromEntity(it) }
    }

    private fun observeRemoteNews(cityName: String)
            : Single<News> {
        return newsDomainRepository.getNews(cityName)
    }
}