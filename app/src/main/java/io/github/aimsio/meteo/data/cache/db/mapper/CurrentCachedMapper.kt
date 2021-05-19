package io.github.aimsio.meteo.data.cache.db.mapper

import io.github.aimsio.meteo.data.cache.db.model.CurrentCached
import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import javax.inject.Inject

class CurrentCachedMapper @Inject constructor() : CacheMapper<CurrentCached, CurrentEntity> {
    override fun mapFromCached(cached: CurrentCached): CurrentEntity {
        return CurrentEntity(cached.id, cached.base, cached.clouds, cached.cod, cached.coord, cached.dt,
        cached.main, cached.name, cached.sys, cached.timezone, cached.visibility, cached.weather, cached.wind)
    }

    override fun mapToCached(cityName: String, entity: CurrentEntity): CurrentCached {
        return CurrentCached(entity.id, cityName, entity.base, entity.clouds, entity.cod, entity.coord,
        entity.dt, entity.main, entity.name, entity.sys, entity.timezone, entity.visibility, entity.weather, entity.wind)
    }
}