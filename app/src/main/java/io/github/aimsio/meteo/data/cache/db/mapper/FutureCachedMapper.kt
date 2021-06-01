package io.github.aimsio.meteo.data.cache.db.mapper

import io.github.aimsio.meteo.data.cache.db.model.FutureCached
import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.model.weather.City
import javax.inject.Inject

class FutureCachedMapper @Inject constructor() : CacheMapper<FutureCached, FutureEntity> {
    override fun mapFromCached(cached: FutureCached): FutureEntity {
        return FutureEntity(cached.id, cached.cityName ,cached.cnt, cached.cod, cached.list, cached.message)
    }

    override fun mapToCached(cityName:String, entity: FutureEntity): FutureCached {
        return FutureCached(entity.id, cityName , entity.cnt, entity.cod, entity.list, entity.message)
    }

}