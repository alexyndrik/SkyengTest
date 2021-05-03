package com.alexyndrik.skyengtest.ui.search

import android.os.Bundle
import com.alexyndrik.skyengtest.R
import com.alexyndrik.skyengtest.WordsApp
import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.ui.base.BaseActivity
import javax.inject.Inject

class SearchWordsActivity: BaseActivity(), SearchWordsContract.View {

    private val TAG = SearchWordsActivity::class.java.simpleName

    @Inject
    lateinit var mPresenter: SearchWordsPresenter

    init {
        WordsApp.mWordsComponent?.inject(this)
        mPresenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_words)
        setUp()
        mPresenter.getWords(this, "fish")
    }

    override fun setUp() {

    }

    override fun onWordsReady(items: List<Word>) {

    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

}