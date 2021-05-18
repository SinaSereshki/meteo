package io.github.aimsio.meteo.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aimsio.meteo.data.cache.db.model.CurrentCached
import io.github.aimsio.meteo.data.cache.db.model.FutureCached
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHED_TABLE
import io.github.aimsio.meteo.data.cache.db.util.FUTURE_CACHED_TABLE
import io.reactivex.Flowable

@Dao
abstract class FutureCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFutureWeather(formula: FutureCached)

    @Query("SELECT * FROM $FUTURE_CACHED_TABLE WHERE cityName=:query")
    abstract fun getFutureWeather(query:String): Flowable<FutureCached>

    @Query("DELETE FROM $FUTURE_CACHED_TABLE")
    abstract fun deleteFutureWeather()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceFutureWeather(futureCached: FutureCached)
}