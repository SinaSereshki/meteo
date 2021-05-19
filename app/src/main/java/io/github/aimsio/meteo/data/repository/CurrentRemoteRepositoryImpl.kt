package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.reactivex.Single
import javax.inject.Inject

class CurrentRemoteRepositoryImpl @Inject constructor(
    private val currentWeather: CurrentRemote
): CurrentRepository {
    override fun getCurrentWeather(cityName: String): Single<CurrentEntity> {
        return currentWeather.getCurrentWeather(cityName)
    }
}