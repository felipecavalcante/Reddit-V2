package com.example.redditv2.app.app.data

import com.example.redditv2.app.app.data.service.RedditService
import com.example.redditv2.app.app.domain.RedditApi
import io.reactivex.Single
import javax.inject.Inject

interface IRepository {
    fun getRedditPost(after: String?): Single<RedditApi>
}

class RedditRepository @Inject constructor(
    private val service: RedditService
) : IRepository {
    override fun getRedditPost(after: String?): Single<RedditApi> =
        service.list(after).map { it.toRedditApi() }
}