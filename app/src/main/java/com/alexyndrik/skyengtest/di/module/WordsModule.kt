package com.alexyndrik.skyengtest.di.module

import com.alexyndrik.skyengtest.data.remote.RemoteDataSource
import com.alexyndrik.skyengtest.di.scope.WordsScope
import com.alexyndrik.skyengtest.ui.meaning_info.MeaningInfoPresenter
import com.alexyndrik.skyengtest.ui.search_words.SearchWordsPresenter
import dagger.Module
import dagger.Provides

@Module
class WordsModule {

    @Provides
    @WordsScope
    fun providesSearchWordsPresenter(remoteDataSource: RemoteDataSource) = SearchWordsPresenter(remoteDataSource)

    @Provides
    @WordsScope
    fun providesMeaningInfoPresenter(remoteDataSource: RemoteDataSource) = MeaningInfoPresenter(remoteDataSource)

}