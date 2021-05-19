package io.github.aimsio.meteo.presentation.ui

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.aimsio.meteo.di.PerActivity
import io.github.aimsio.meteo.di.PerFragment
import io.github.aimsio.meteo.presentation.ui.search.SearchFragment
import io.github.aimsio.meteo.presentation.ui.search.SearchFragmentModule

@Module
abstract class MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    internal abstract fun exploreFragment(): SearchFragment

    @Binds
    @PerActivity
    internal abstract fun mainNavigator(mainActivity: MainActivity): MainNavigator
}
