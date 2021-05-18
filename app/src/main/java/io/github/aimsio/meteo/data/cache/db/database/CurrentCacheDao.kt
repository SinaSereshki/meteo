package io.github.aimsio.meteo.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aimsio.meteo.data.cache.db.model.CurrentCacheInfo
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHED_TABLE
import io.github.aimsio.meteo.data.cache.db.util.CURRENT_CACHE_INFO_TABLE
import io.reactivex.Maybe

@Dao
abstract class CurrentCacheDao {

    @Query("SELECT * FROM $CURRENT_CACHED_TABLE WHERE city=:query")
    abstract fun getCurrentCache(query:String): Maybe<CurrentCacheInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceCurrentCache(cacheInfo: CurrentCacheInfo)

}