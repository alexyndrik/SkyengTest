package com.alexyndrik.skyengtest.ui.search_words

import android.util.Log
import com.alexyndrik.skyengtest.data.remote.RemoteDataSource
import com.alexyndrik.skyengtest.ui.base.BasePresenter
import com.alexyndrik.skyengtest.ui.meaning_info.MeaningInfoPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchWordsPresenter(
    private val mRemoteDataSource: RemoteDataSource
): BasePresenter<SearchWordsContract.View>(), SearchWordsContract.Presenter {

    private val TAG = MeaningInfoPresenter::class.java.simpleName

    override fun getWords(search: String, page: Int?, pageSize: Int?) {
        mView?.showLoading()
        mDisposable = mRemoteDataSource.getWords(search, page, pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (!isViewAttached()) return@subscribe

                    mView?.hideLoading()
                    if (response.isSuccessful) {
                        mView?.onWordsReady(response.body()?.toList()!!)
                    }
                },
                { error ->
                    mView?.hideLoading()
                    Log.e(TAG, error.message ?: "")
                },
                {
                    mView?.hideLoading()
                    Log.d(TAG, "completed")
                }
            )
    }

}