package com.alexyndrik.skyengtest.di.component

import android.app.Application
import com.alexyndrik.skyengtest.MvpApp
import com.alexyndrik.skyengtest.di.builder.ActivityBuilder
import com.alexyndrik.skyengtest.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MvpApp)

}