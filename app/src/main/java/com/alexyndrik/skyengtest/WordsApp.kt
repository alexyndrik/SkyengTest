package com.alexyndrik.skyengtest

import android.app.Application
import com.alexyndrik.skyengtest.di.component.DaggerAppComponent
import com.alexyndrik.skyengtest.di.component.DaggerWordsComponent
import com.alexyndrik.skyengtest.di.component.WordsComponent
import com.alexyndrik.skyengtest.di.module.AppModule
import com.alexyndrik.skyengtest.di.module.WordsModule

class WordsApp : Application() {

    companion object {
        var mWordsComponent: WordsComponent? = null
    }

    init {
        mWordsComponent = DaggerWordsComponent.builder()
            .appComponent(DaggerAppComponent.builder()
                .appModule(AppModule(this)).build()
            ).wordsModule(WordsModule()).build()
    }

}