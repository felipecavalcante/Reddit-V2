package com.example.redditv2.app.base.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("baseUrl") baseUrl: String, client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }


    //Note: JvmSuppressWildcards is necessary due to this: https://github.com/google/dagger/issues/668
    @Singleton
    @Provides
    @JvmSuppressWildcards
    fun provideOkHttpClient(interceptors: Set<Interceptor>): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        interceptors.forEach {
            okHttpBuilder.addInterceptor(it)
        }
        return okHttpBuilder.build()
    }


    @Singleton
    @IntoSet
    @Provides
    fun provideLoggingInterceptor(@Named("isDebuggable") debug: Boolean): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (debug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

}