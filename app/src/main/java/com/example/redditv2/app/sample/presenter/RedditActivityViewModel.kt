package com.example.redditv2.app.sample.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RedditActivityViewModel @Inject constructor(

) : ViewModel() {
    val thumbs = MutableLiveData<Unit>()

}