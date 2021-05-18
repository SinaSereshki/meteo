package io.github.aimsio.meteo.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aimsio.meteo.data.cache.db.model.CurrentCacheInfo
import io.github.aimsio.meteo.data.cache.db.model.CurrentCached
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHED_TABLE
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHE_INFO_TABLE
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
abstract class CurrentCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCurrentWeather(formula: CurrentCached)

    @Query("SELECT * FROM $CURRENT_CACHED_TABLE WHERE city=:query")
    abstract fun getCurrentWeather(query:String): Flowable<CurrentCached>

    @Query("DELETE FROM $CURRENT_CACHED_TABLE")
    abstract fun deleteCurrentWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceCurrentWeather(photos: CurrentCached)

}