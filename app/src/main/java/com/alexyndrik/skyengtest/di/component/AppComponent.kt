package com.alexyndrik.skyengtest.di.component

import com.alexyndrik.skyengtest.data.remote.RemoteDataSource
import com.alexyndrik.skyengtest.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun remoteDataSource(): RemoteDataSource

}