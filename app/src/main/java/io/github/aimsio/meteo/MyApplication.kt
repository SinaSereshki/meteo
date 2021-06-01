package io.github.aimsio.meteo

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.aimsio.meteo.di.DaggerAppComponent

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}