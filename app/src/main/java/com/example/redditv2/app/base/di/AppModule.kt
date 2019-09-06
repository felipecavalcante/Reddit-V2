package com.example.redditv2.app.base.di

import com.example.redditv2.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    @Provides
    @Singleton
    @Named("isDebuggable")
    fun provideIsDebuggable() = BuildConfig.DEBUG

    companion object{
        const val BASE_URL = "https://www.reddit.com/r/Android/"
    }
}