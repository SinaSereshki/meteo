package io.github.aimsio.meteo.data.network

import io.github.aimsio.meteo.data.model.news.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    fun getNews(
        @Query("q") city: String,
        @Query("pageSize") count: Int,
        @Query("page") page: Int,
        @Query("apiKey") key: String = "<YOUR_TOKEN>"
    ): Single<News>
}