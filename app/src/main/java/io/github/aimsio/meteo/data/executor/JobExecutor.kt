package io.github.aimsio.meteo.data.executor

import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class JobExecutor @Inject constructor() : ThreadExecutor {
    private val threadFactory: ThreadFactory
    private val threadPoolExecutor: ThreadPoolExecutor
    private val workQueue = LinkedBlockingDeque<Runnable>()

    init {
        this.threadFactory = JobThreadFactory()
        this.threadPoolExecutor = ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            this.workQueue,
            this.threadFactory
        )
    }

    override fun execute(command: Runnable?) {
        command?.let { threadPoolExecutor.execute(it) }
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(r: Runnable?) = Thread(r, THREAD_NAME + counter++)

        companion object {
            private const val THREAD_NAME = "meteo_"
        }
    }

    companion object {
        private const val CORE_POOL_SIZE = 3
        private const val MAXIMUM_POOL_SIZE = 5
        private const val KEEP_ALIVE_TIME = 10L
    }
}