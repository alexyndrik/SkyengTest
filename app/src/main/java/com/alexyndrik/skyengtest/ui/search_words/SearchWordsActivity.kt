package com.alexyndrik.skyengtest.ui.search_words

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexyndrik.skyengtest.R
import com.alexyndrik.skyengtest.WordsApp
import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.ui.base.BaseActivity
import javax.inject.Inject

class SearchWordsActivity: BaseActivity(), SearchWordsContract.View {

    private val TAG = SearchWordsActivity::class.java.simpleName

    @Inject
    lateinit var mPresenter: SearchWordsPresenter

    private lateinit var listWords: RecyclerView
    private lateinit var noWordsFoundPanel: LinearLayout

    init {
        WordsApp.mWordsComponent?.inject(this)
        mPresenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_words)
        setUp()
    }

    override fun setUp() {
        progressBar = findViewById(R.id.progress_bar)
        noWordsFoundPanel = findViewById(R.id.no_words_found_panel)

        listWords = findViewById(R.id.list_words)
        listWords.setHasFixedSize(true)
        listWords.layoutManager = LinearLayoutManager(this)
        listWords.adapter = SearchWordsAdapter(this)

        val searchView = findViewById<SearchView>(R.id.search_word)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                showLoading()
                mPresenter.getWords(newText ?: "")
                return false
            }
        })

        showData(listOf())
    }

    override fun onWordsReady(items: List<Word>) {
        hideLoading()
        (listWords.adapter as SearchWordsAdapter).apply {
            setItems(items)
            notifyDataSetChanged()
        }
        showData(items)
    }

    private fun showData(items: List<Word>) {
        if (items.isEmpty()) {
            listWords.visibility = View.GONE
            noWordsFoundPanel.visibility = View.VISIBLE
        } else {
            listWords.visibility = View.VISIBLE
            noWordsFoundPanel.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

}