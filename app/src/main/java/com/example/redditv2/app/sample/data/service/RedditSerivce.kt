package com.example.redditv2.app.sample.data.service

import com.example.redditv2.app.sample.data.model.RedditApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService{

    @GET("new/.json")
    fun list(@Query ("limit") limit: Int,
             @Query("after") after: String?): Single<RedditApiResponse>
}
