package io.github.aimsio.meteo.data.network

import io.github.aimsio.meteo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkFactory @Inject constructor() {
    fun <T> createWeather(serviceClass: Class<T>): T = retrofitWeather().create(serviceClass)
    fun <T> createNews(serviceClass: Class<T>): T = retrofitNews().create(serviceClass)

    private fun retrofitWeather() = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient())
        .build()

    private fun retrofitNews() = Retrofit.Builder()
        .baseUrl(BuildConfig.NEWS_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient())
        .build()

    private fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = loggingLevel })
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private val loggingLevel =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}
