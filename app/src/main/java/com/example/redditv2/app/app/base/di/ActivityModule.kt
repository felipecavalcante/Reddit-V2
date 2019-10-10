package com.example.redditv2.app.app.base.di

import com.example.redditv2.app.app.base.annotation.ActivityScope
import com.example.redditv2.app.app.presenter.RedditActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [RedditActivityModule::class])
    abstract fun contributesReddiActivity(): RedditActivity
}