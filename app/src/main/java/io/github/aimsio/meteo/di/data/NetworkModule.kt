package io.github.aimsio.meteo.di.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.aimsio.meteo.data.network.NetworkFactory
import io.github.aimsio.meteo.data.network.NewsApi
import io.github.aimsio.meteo.data.network.WeatherApi
import io.github.aimsio.meteo.data.repository.CurrentRemote
import io.github.aimsio.meteo.data.repository.CurrentRemoteImpl


@Module
abstract class NetworkModule {

    companion object {
        @Provides
        fun weatherApi(networkFactory: NetworkFactory): WeatherApi {
            return networkFactory.createWeather(WeatherApi::class.java)
        }

        @Provides
        fun newsApi(networkFactory: NetworkFactory): NewsApi {
            return networkFactory.createNews(NewsApi::class.java)
        }
    }

    @Binds
    abstract fun bindCurrentCache(currentRemote: CurrentRemoteImpl): CurrentRemote

//    @Binds
//    abstract fun bindPhotosCache(photosRemote: SearchRemoteImpl): SearchRemote


}