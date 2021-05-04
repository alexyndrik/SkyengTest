package com.alexyndrik.skyengtest.ui.meaning_info

import com.alexyndrik.skyengtest.data.remote.model.Meaning
import com.alexyndrik.skyengtest.ui.base.IBaseView

class MeaningInfoContract {

    interface Presenter {

        fun getMeanings(ids: List<String>, updatedAt: String? = null)

    }

    interface View : IBaseView {

        fun onMeaningsReady(items: List<Meaning>)

    }

}