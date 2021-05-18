package io.github.aimsio.meteo.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aimsio.meteo.data.cache.db.model.FutureCacheInfo
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHED_TABLE
import io.reactivex.Maybe

@Dao
abstract class FutureCachedInfoDao {

    @Query("SELECT * FROM $CURRENT_CACHED_TABLE WHERE city=:query")
    abstract fun getFutureCacheInfo(query:String): Maybe<FutureCacheInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceFutureCacheInfo(cacheInfo: FutureCacheInfo)
}