package com.example.redditv2.app.base

import android.app.Activity
import android.app.Application
import com.example.redditv2.app.base.di.AppComponent
import com.example.redditv2.app.base.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponentCreator: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponentCreator = DaggerAppComponent.builder()
            .applicationBind(this)
            .build()

        appComponentCreator.inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> = activityInjector


}
