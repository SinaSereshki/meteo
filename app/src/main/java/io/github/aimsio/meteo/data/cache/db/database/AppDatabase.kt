package io.github.aimsio.meteo.data.cache.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.aimsio.meteo.data.cache.db.model.*
import io.github.aimsio.meteo.data.cache.db.util.APP_DATABASE

@Database(entities = [CurrentCached::class, CurrentCacheInfo::class, FutureCached::class, FutureCacheInfo::class, NewsCached::class, NewsCacheInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun currentCachedInfoDao(): CurrentCachedInfoDao
    abstract fun futureCachedInfoDao(): FutureCachedInfoDao
    abstract fun newsCachedInfoDao(): NewsCachedInfoDao


    abstract fun currentCacheDao(): CurrentCacheDao
    abstract fun futureCacheDao(): FutureCacheDao
    abstract fun newsCacheDao(): NewsCacheDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE).build()
        }

        fun destroyInstance() {
            instance = null
        }

    }


}