package io.github.aimsio.meteo.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.aimsio.meteo.data.model.news.News
import io.github.aimsio.meteo.data.repository.NewsCache
import io.github.aimsio.meteo.data.repository.NewsCheckRepository
import io.github.aimsio.meteo.data.repository.NewsRepository
import io.github.aimsio.meteo.domain.executor.PostExecutionThread
import io.github.aimsio.meteo.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.meikiem.pixabay.presentation.extension.map
import me.meikiem.pixabay.presentation.extension.switchMap
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val newsCheckRepository: NewsCheckRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    private val newsCache: NewsCache
) : ViewModel() {
    var isCachedAndNotExpired = MutableLiveData<Boolean>()
    lateinit var result:MutableLiveData<Result<News>>
    val news = MutableLiveData<News>()


    val query = MutableLiveData("city")

    private var disposable = CompositeDisposable()

    fun checkCacheStatus(query: String) {
        disposable.add(
            Single.zip(
                newsCache.isNewsCached(query),
                newsCache.isNewsCacheExpired(query),
            { areCached, isExpired ->
                Pair(areCached, isExpired)
            }
        ).flatMap { s -> Single.just(s.first && !s.second) }.subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler())
            .subscribe({ result ->
                if (result != null)
                    isCachedAndNotExpired.postValue(result)
                else
                    isCachedAndNotExpired.postValue(false)
            }, { result ->
                result.printStackTrace()
                isCachedAndNotExpired.postValue(false)
            }))
    }

     var getNews: LiveData<News> = isCachedAndNotExpired.map {
        newsCheckRepository.observeNews(
            query.value.toString(), 0, it
        ).blockingGet()
    }

}