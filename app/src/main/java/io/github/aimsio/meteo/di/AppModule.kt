package io.github.aimsio.meteo.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.aimsio.meteo.presentation.ui.MainActivity
import io.github.aimsio.meteo.presentation.ui.MainActivityModule

@Module
abstract class AppModule{
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    @Binds
    abstract fun bindContext(application: Application): Context

}