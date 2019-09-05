package com.example.redditv2.app.sample.di

import androidx.lifecycle.ViewModel
import com.example.redditv2.app.base.annotation.ActivityScope
import com.example.redditv2.app.base.annotation.ViewModelKey
import com.example.redditv2.app.sample.data.service.RedditService
import com.example.redditv2.app.sample.presenter.RedditActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class RedditActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(RedditActivityViewModel::class)
    abstract fun providesViewModel(viewModel: RedditActivityViewModel): ViewModel


    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideRetrofit(retrofit: Retrofit): RedditService = retrofit.create()
    }
}