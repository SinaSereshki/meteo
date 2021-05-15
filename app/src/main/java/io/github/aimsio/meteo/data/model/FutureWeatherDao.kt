package io.github.aimsio.meteo.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aimsio.meteo.data.model.weather.FUTURE_WEATHER_ID
import io.github.aimsio.meteo.data.model.weather.FutureWeather

@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(futureWeather: FutureWeather)

    @Query("SELECT * FROM future_weather WHERE id = $FUTURE_WEATHER_ID")
    fun getWeather(): LiveData<FutureWeather>
}