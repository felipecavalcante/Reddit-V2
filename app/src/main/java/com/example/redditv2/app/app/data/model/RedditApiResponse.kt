package com.example.redditv2.app.app.data.model

import com.example.redditv2.app.app.domain.RedditApi

data class RedditApiResponse(val data: ChildrenResponse) {
    fun toRedditApi(): RedditApi = RedditApi(data = data.toDataResponse())
}
