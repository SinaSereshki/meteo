package io.github.aimsio.meteo.data.store

import io.github.aimsio.meteo.data.entity.model.CurrentEntity
import io.github.aimsio.meteo.data.repository.CurrentDataStore
import io.github.aimsio.meteo.data.repository.CurrentRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CurrentRemoteDataStore @Inject constructor(
    private val currentRemote: CurrentRemote
):CurrentDataStore{
    override fun getCurrentWeather(cityName: String): Single<CurrentEntity> {
        return currentRemote.getCurrentWeather(cityName)
    }

    override fun saveCurrentWeather(cityName: String, currentWeather: CurrentEntity): Completable {
        throw UnsupportedOperationException("Saving current weather isn't supported here...")
    }

    override fun clearCurrentWeather(): Completable {
        throw UnsupportedOperationException("Saving current weather isn't supported here...")
    }
}