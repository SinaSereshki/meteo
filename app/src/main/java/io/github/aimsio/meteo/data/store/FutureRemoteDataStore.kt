package io.github.aimsio.meteo.data.store

import io.github.aimsio.meteo.data.entity.model.FutureEntity
import io.github.aimsio.meteo.data.repository.FutureDataStore
import io.github.aimsio.meteo.data.repository.FutureRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FutureRemoteDataStore @Inject constructor(
    private val futureRemote: FutureRemote
): FutureDataStore {
    override fun getFutureWeather(cityName: String): Single<FutureEntity> {
        return futureRemote.getFutureWeather(cityName)
    }

    override fun saveFutureWeather(cityName: String, futureWeather: FutureEntity): Completable {
        throw UnsupportedOperationException("Saving future weather isn't supported here...")
    }

    override fun clearFutureWeather(): Completable {
        throw UnsupportedOperationException("Saving future weather isn't supported here...")
    }
}