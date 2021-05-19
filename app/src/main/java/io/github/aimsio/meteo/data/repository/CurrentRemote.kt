package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.reactivex.Single

interface CurrentRemote {

    fun getCurrentWeather(cityName:String):Single<CurrentEntity>
}