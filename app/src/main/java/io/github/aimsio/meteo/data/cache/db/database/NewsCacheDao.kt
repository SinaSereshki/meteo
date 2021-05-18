package io.github.aimsio.meteo.data.cache.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aimsio.meteo.data.cache.db.model.CurrentCached
import io.github.aimsio.meteo.data.cache.db.model.FutureCached
import io.github.aimsio.meteo.data.cache.db.model.NewsCached
import io.github.aimsio.meteo.data.cache.db.util.FUTURE_CACHED_TABLE
import io.github.aimsio.meteo.data.cache.db.util.NEWS_CACHED_TABLE
import io.reactivex.Flowable

@Dao
abstract class NewsCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNews(formula: NewsCached)

    @Query("SELECT * FROM $NEWS_CACHED_TABLE WHERE cityName=:query")
    abstract fun getNews(query:String): Flowable<NewsCached>

    @Query("DELETE FROM $NEWS_CACHED_TABLE")
    abstract fun deleteNews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun replaceNews(photos: NewsCached)
}