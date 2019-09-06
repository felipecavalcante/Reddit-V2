package com.example.redditv2.app.base.di

import com.example.redditv2.app.base.annotation.ActivityScope
import com.example.redditv2.app.sample.di.RedditActivityModule
import com.example.redditv2.app.sample.presenter.RedditActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [RedditActivityModule::class])
    abstract fun contributesReddiActivity(): RedditActivity
}