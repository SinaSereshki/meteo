package io.github.aimsio.meteo.di.data

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.aimsio.meteo.data.cache.db.CurrentCacheImpl
import io.github.aimsio.meteo.data.cache.db.FutureCacheImpl
import io.github.aimsio.meteo.data.cache.db.NewsCacheImpl
import io.github.aimsio.meteo.data.cache.db.database.AppDatabase
import io.github.aimsio.meteo.data.repository.CurrentCache
import io.github.aimsio.meteo.data.repository.FutureCache
import io.github.aimsio.meteo.data.repository.NewsCache

@Module
abstract class CacheModule {

    companion object {
        @Provides
        fun provideDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindCurrentCache(photos: CurrentCacheImpl): CurrentCache

    @Binds
    abstract fun bindFutureCache(photos: FutureCacheImpl): FutureCache

    @Binds
    abstract fun bindNewsCache(photos: NewsCacheImpl): NewsCache

}