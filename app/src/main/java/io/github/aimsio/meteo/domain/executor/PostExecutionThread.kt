package io.github.aimsio.meteo.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun scheduler(): Scheduler
}