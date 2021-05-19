package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.reactivex.Single

interface CurrentRepository {
    fun getCurrentWeather(cityName:String): Single<CurrentEntity>
}
