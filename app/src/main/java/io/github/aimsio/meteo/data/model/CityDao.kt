package io.github.aimsio.meteo.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.aimsio.meteo.data.model.weather.CITY_ID
import io.github.aimsio.meteo.data.model.weather.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(city: City)

    @Query("SELECT * FROM city WHERE id = $CITY_ID")
    fun getCity(): LiveData<City>
}