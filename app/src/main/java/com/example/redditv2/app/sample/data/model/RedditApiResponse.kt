package com.example.redditv2.app.sample.data.model

import com.example.redditv2.app.sample.domain.RedditApi

data class RedditApiResponse(val data: ChildrenResponse) {
    fun toRedditApi(): RedditApi = RedditApi(data = data.toDataResponse())
}
