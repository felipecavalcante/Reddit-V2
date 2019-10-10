package com.example.redditv2.app.app.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditv2.app.app.data.IRepository
import com.example.redditv2.app.app.domain.ChildrenData
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class RedditActivityViewModel @Inject constructor(
    private val repository: IRepository,
    @Named("IO") private val ioScheduler: Scheduler,
    @Named("Main") private val mainScheduler: Scheduler
) : ViewModel() {
    private val disposable = CompositeDisposable()
    val afterCall = MutableLiveData<String>().apply { value = null }
    val showLoading = MutableLiveData<Boolean>()
    val conection = MutableLiveData<Boolean>()
    lateinit var list: List<ChildrenData>

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
    init {
        loading()
    }

    fun loading() {
        disposable.add(
            repository.getRedditPost(after = afterCall.value)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .doOnSubscribe { showLoading.value = true }
                .doFinally { showLoading.value = false }
                .subscribe({ api ->
                    if (api.data.children.isNullOrEmpty()) {
                        conection.value = false
                    } else {
                        list = api.data.children
                        afterCall.value = api.data.after
                    }
                    conection.value = true
                }, {
                    conection.value = false
                })
        )
    }
}
