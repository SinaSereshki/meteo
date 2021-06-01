package io.github.aimsio.meteo.data.mapper

import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.model.weather.FutureWeather
import javax.inject.Inject

class FutureMapper @Inject constructor() : EntityMapper<FutureEntity, FutureWeather>  {
    override fun mapFromEntity(entity: FutureEntity): FutureWeather {
        return  FutureWeather(entity.id, entity.cityName, entity.cnt, entity.cod, entity.list, entity.message)
    }

    override fun mapToEntity(domain: FutureWeather): FutureEntity {
        return  FutureEntity(domain.id, domain.city, domain.cnt, domain.cod, domain.list, domain.message)
    }
}