package com.example.redditv2.data.model

import com.example.redditv2.domain.RedditApi

data class RedditApiResponse(val data: ChildrenResponse) {
    fun toRedditResponse(): RedditApi = RedditApi(data = data.toDataResponse())
}
