package com.example.redditv2.di

import com.example.redditv2.presenter.RedditActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class RedditModule{

    @Provides
    @ContributesAndroidInjector()
    abstract fun constributesRedditActivity(): RedditActivity


}