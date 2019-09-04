package com.example.redditv2.di

import com.example.redditv2.data.service.RedditService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
class RedditActivityModule{

    @Provides
    fun provideRetrofit(retrofit: Retrofit): RedditService = retrofit.create()
}