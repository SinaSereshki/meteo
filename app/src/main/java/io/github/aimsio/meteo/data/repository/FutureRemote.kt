package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.reactivex.Single

interface FutureRemote {

    fun getFutureWeather(cityName:String):Single<FutureEntity>
}