package com.alexyndrik.skyengtest.di.module

import android.app.Application
import android.content.Context
import com.alexyndrik.skyengtest.BuildConfig
import com.alexyndrik.skyengtest.data.remote.RemoteDataSource
import com.alexyndrik.skyengtest.data.remote.RemoteService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(application: Application) {

    var mApplication : Application? = application

    @Provides
    fun providesAppContext(): Context = mApplication!!

    @Provides
    @Singleton
    fun providesRemoteService(): RemoteService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient()).build()
            .create(RemoteService::class.java)
    }

    private fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val mUrl = originalHttpUrl.newBuilder().build()

            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .url(mUrl)
                .build()

            val response = chain.proceed(request)
            response.cacheResponse()
            response
        }
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providesRemoteDataSource(remoteService: RemoteService) = RemoteDataSource(remoteService)

}