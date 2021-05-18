package io.github.aimsio.meteo.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.aimsio.meteo.MyApplication
import io.github.aimsio.meteo.di.data.CacheModule
import io.github.aimsio.meteo.di.data.DataModule
import io.github.aimsio.meteo.di.data.NetworkModule
import io.github.aimsio.meteo.di.data.RepositoryModule
import io.github.aimsio.meteo.di.domain.ExecutionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DataModule::class,
        ExecutionModule::class,
        CacheModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: MyApplication)
}