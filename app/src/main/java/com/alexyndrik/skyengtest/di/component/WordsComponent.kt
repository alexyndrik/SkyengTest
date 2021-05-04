package com.alexyndrik.skyengtest.di.component

import com.alexyndrik.skyengtest.di.module.WordsModule
import com.alexyndrik.skyengtest.di.scope.WordsScope
import com.alexyndrik.skyengtest.ui.meaning_info.MeaningInfoActivity
import com.alexyndrik.skyengtest.ui.search_words.SearchWordsActivity
import dagger.Component

@WordsScope
@Component(modules = [WordsModule::class], dependencies = [AppComponent::class])
interface WordsComponent {

    fun inject(view: SearchWordsActivity)

    fun inject(view: MeaningInfoActivity)

}