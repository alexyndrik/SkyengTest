package com.alexyndrik.skyengtest.ui.search

import android.content.Context
import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.ui.base.IBaseView

class SearchWordsContract {

    interface Presenter {

        fun getWords(context: Context, search: String, page: Int? = null, pageSize: Int? = null)

        fun getWordsFromApi(search: String, page: Int? = null, pageSize: Int? = null)

        fun getWordsFromDb(search: String, page: Int? = null, pageSize: Int? = null)

        fun saveWords(items: List<Word>)

    }

    interface View : IBaseView {

        fun onWordsReady(items: List<Word>)

    }

}