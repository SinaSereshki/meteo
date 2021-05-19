package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.reactivex.Single
import javax.inject.Inject

class FutureRemoteRepositoryImpl @Inject constructor(
    private val futureWeather: FutureRemote
): FutureRepository {
    override fun getFutureWeather(cityName: String): Single<FutureEntity> {
        return futureWeather.getFutureWeather(cityName)
    }
}