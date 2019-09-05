package com.example.redditv2.app.base.di

import android.app.Application
import com.example.redditv2.app.base.annotation.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DependencyInjection::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    fun inject(app: Application)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun applicationBind(application: Application): Builder
    }
}