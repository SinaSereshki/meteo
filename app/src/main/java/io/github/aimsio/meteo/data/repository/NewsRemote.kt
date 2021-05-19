package io.github.aimsio.meteo.data.repository

import io.github.aimsio.meteo.data.entity.model.NewsEntity
import io.reactivex.Single

interface NewsRemote {

    fun getNews(cityName:String):Single<NewsEntity>
}