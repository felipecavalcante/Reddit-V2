package com.example.redditv2.app.sample.data

import com.example.redditv2.app.sample.data.service.RedditService
import com.example.redditv2.app.sample.domain.RedditApi
import io.reactivex.Single
import java.lang.StringBuilder
import javax.inject.Inject

interface IRepository {
    fun getRedditPost(limit: Int, after: String): Single<RedditApi>
}

class RedditRepository @Inject constructor(
    private val service: RedditService
) : IRepository {
    override fun getRedditPost(limit: Int, after: String): Single<RedditApi> =
        service.list(limit, after).map { it.toRedditApi() }
}