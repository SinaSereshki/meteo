package io.github.aimsio.meteo.di.domain

import dagger.Binds
import dagger.Module
import io.github.aimsio.meteo.data.executor.JobExecutor
import io.github.aimsio.meteo.domain.executor.PostExecutionThread
import io.github.aimsio.meteo.domain.executor.ThreadExecutor
import io.github.aimsio.meteo.presentation.executor.UiThread

@Module
abstract class ExecutionModule {
    @Binds
    abstract fun threadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun postExecutionThread(uiThread: UiThread): PostExecutionThread
}