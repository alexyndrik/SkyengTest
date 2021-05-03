package com.alexyndrik.skyengtest.ui.search

import android.content.Context
import android.util.Log
import com.alexyndrik.skyengtest.data.remote.RemoteDataSource
import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchWordsPresenter(
    private val mRemoteDataSource: RemoteDataSource
): BasePresenter<SearchWordsContract.View>(), SearchWordsContract.Presenter {

    private val TAG = SearchWordsPresenter::class.java.simpleName

    override fun getWords(context: Context, search: String, page: Int?, pageSize: Int?) {
        getWordsFromApi(search, page, pageSize)
    }

    override fun getWordsFromApi(search: String, page: Int?, pageSize: Int?) {
        mView?.showLoading()
        mDisposable = mRemoteDataSource.getWords(search, page, pageSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (!isViewAttached()) return@subscribe

                    mView?.hideLoading()
                    if (response.isSuccessful) {
                        saveWords(response.body()?.toList()!!)
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

    override fun getWordsFromDb(search: String, page: Int?, pageSize: Int?) {

    }

    override fun saveWords(items: List<Word>) {

    }

}