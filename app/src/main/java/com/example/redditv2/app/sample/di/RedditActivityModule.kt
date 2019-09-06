package com.example.redditv2.app.sample.di

import androidx.lifecycle.ViewModel
import com.example.redditv2.app.base.annotation.ActivityScope
import com.example.redditv2.app.base.annotation.ViewModelKey
import com.example.redditv2.app.sample.data.IRepository
import com.example.redditv2.app.sample.data.RedditRepository
import com.example.redditv2.app.sample.data.service.RedditService
import com.example.redditv2.app.sample.presenter.RedditActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create

@Module
class RedditActivityModule {

    @Provides
    @IntoMap
    @ActivityScope
    @ViewModelKey(RedditActivityViewModel::class)
    fun providesViewModel(viewModel: RedditActivityViewModel): ViewModel = viewModel

    @Provides
    @ActivityScope
    fun provideRetrofit(retrofit: Retrofit): RedditService = retrofit.create()

    @Provides
    @ActivityScope
    fun provideRepository(repository: RedditRepository): IRepository = repository
}