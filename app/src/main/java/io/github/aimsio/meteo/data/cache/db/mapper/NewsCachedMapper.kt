package io.github.aimsio.meteo.data.cache.db.mapper

import io.github.aimsio.meteo.data.cache.db.model.NewsCached
import io.github.aimsio.meteo.data.entity.model.NewsEntity
import javax.inject.Inject

class NewsCachedMapper @Inject constructor() : CacheMapper<NewsCached, NewsEntity> {
    override fun mapFromCached(cached: NewsCached): NewsEntity {
        return NewsEntity(cached.id, cached.articles, cached.status, cached.totalResults)
    }

    override fun mapToCached(cityName: String, entity: NewsEntity): NewsCached {
        return NewsCached(entity.id, cityName, entity.articles, entity.status, entity.totalResults)
    }
}