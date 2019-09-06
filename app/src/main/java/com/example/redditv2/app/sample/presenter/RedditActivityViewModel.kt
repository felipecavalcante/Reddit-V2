package com.example.redditv2.app.sample.presenter

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditv2.app.sample.data.IRepository
import com.example.redditv2.app.sample.domain.ChildrenData
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RedditActivityViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {
    private val disposable = CompositeDisposable()
    val after = MutableLiveData<String>().apply { value = "" }
    val limit = MutableLiveData<Int>().apply { value = 10 }
    val conection = MutableLiveData<Boolean>()
    lateinit var list: List<ChildrenData>

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun loading() {
        disposable.add(
            repository.getRedditPost(limit = limit.value!!, after = after.value!!)
                .subscribe({api ->
                    list = api.data.children.map {
                        it
                    }
                }, {
                    if (it is NetworkErrorException) {
                        conection.value = false
                    }
                })
        )
    }
}