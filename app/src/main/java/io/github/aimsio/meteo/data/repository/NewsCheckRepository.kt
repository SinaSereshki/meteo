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
        pageNumber: Int,
        isCachedAndNotExpired:Boolean
    ): Single<News> {
        return if(isCachedAndNotExpired)
            observeLocalNews(cityName,pageNumber
            )
        else
            observeRemoteNews(cityName,pageNumber)
    }



    private fun observeLocalNews(cityName: String, pageNumber: Int): Single<News> {
        return newsCacheDataStore.getNews(cityName, pageNumber).map { mapper.mapFromEntity(it) }
    }

    private fun observeRemoteNews(cityName: String, pageNumber: Int)
            : Single<News> {
        return newsDomainRepository.getNews(cityName, pageNumber)
    }
}