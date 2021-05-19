package io.github.aimsio.meteo.data.mapper

import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.github.aimsio.meteo.data.model.news.News
import io.github.aimsio.meteo.data.model.weather.FutureWeather
import javax.inject.Inject

class NewsMapper @Inject constructor() : EntityMapper<NewsEntity, News>  {
    override fun mapFromEntity(entity: NewsEntity): News {
        return  News(entity.articles, entity.status, entity.totalResults)
    }

    override fun mapToEntity(domain: News): NewsEntity {
        return  NewsEntity(domain.articles, domain.status, domain.totalResults)
    }
}