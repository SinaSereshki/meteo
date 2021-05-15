package io.github.aimsio.meteo.data.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.aimsio.meteo.data.model.weather.City
import io.github.aimsio.meteo.data.model.weather.CurrentWeather
import io.github.aimsio.meteo.data.model.weather.FutureWeather

@Database(
    entities = [CurrentWeather::class, FutureWeather::class, City::class],
    version = 1
)

abstract class MeteoDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun futureWeatherDao(): FutureWeatherDao
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile private var instance: MeteoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MeteoDatabase::class.java, "weather.db")
                .build()
    }
}