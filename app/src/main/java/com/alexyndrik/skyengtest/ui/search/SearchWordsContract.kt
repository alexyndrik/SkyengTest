package com.alexyndrik.skyengtest.ui.search

import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.ui.base.IBaseView

class SearchWordsContract {

    interface Presenter {

        fun getWords(search: String, page: Int? = null, pageSize: Int? = null)

    }

    interface View : IBaseView {

        fun onWordsReady(items: List<Word>)

    }

}