package com.example.redditv2.data

import com.example.redditv2.data.service.RedditService
import com.example.redditv2.domain.RedditApi
import io.reactivex.Single
import javax.inject.Inject

interface IRepository {
    fun getRedditPost(): Single<RedditApi>
}

class RedditRepository @Inject constructor(
    private val service: RedditService
) : IRepository {
    override fun getRedditPost(): Single<RedditApi> =
        service.list(10, "").map { it.toRedditResponse() }
}