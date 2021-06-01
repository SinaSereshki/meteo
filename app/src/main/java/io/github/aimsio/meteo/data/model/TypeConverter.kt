package io.github.aimsio.meteo.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.aimsio.meteo.data.model.weather.Weather
import java.lang.reflect.Type
import java.util.*


class TypeConverter {

    companion object {
        @TypeConverter
        fun stringToWeather(data: String?): List<Weather> {
            if (data == null) {
                return Collections.emptyList()
            }
            var gson = Gson()
            val listType: Type = object : TypeToken<List<Weather?>?>() {}.getType()
            return gson.fromJson<List<Weather>>(data, listType)
        }

        @TypeConverter
        fun weatherListToString(weathers: List<Weather?>?): String {
            var gson = Gson()
            return gson.toJson(weathers)
        }
    }
}