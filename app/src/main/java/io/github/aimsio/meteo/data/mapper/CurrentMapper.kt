package io.github.aimsio.meteo.data.mapper

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import javax.inject.Inject

class CurrentMapper @Inject constructor() : EntityMapper<CurrentEntity, CurrentWeather>  {
    override fun mapFromEntity(entity: CurrentEntity): CurrentWeather {
        return  CurrentWeather(entity.base, entity.clouds, entity.cod, entity.coord, entity.dt, entity.id, entity.main,
        entity.name, entity.sys, entity.timezone, entity.visibility, entity.weather, entity.wind)
    }

    override fun mapToEntity(domain: CurrentWeather): CurrentEntity {
        return  CurrentEntity(domain.id, domain.base, domain.clouds, domain.cod, domain.coord, domain.dt, domain.main,
        domain.name, domain.sys, domain.timezone, domain.visibility, domain.weather, domain.wind)
    }
}