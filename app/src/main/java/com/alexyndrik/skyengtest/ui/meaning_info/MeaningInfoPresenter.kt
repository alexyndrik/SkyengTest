package com.alexyndrik.skyengtest.ui.meaning_info

import android.util.Log
import com.alexyndrik.skyengtest.data.remote.RemoteDataSource
import com.alexyndrik.skyengtest.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MeaningInfoPresenter(
    private val mRemoteDataSource: RemoteDataSource
): BasePresenter<MeaningInfoContract.View>(), MeaningInfoContract.Presenter {

    private val TAG = MeaningInfoPresenter::class.java.simpleName

    override fun getMeanings(ids: List<String>, updatedAt: String?) {
        mView?.showLoading()
        mDisposable = mRemoteDataSource.getMeanings(ids.joinToString { it }, updatedAt)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (!isViewAttached()) return@subscribe

                    mView?.hideLoading()
                    if (response.isSuccessful) {
                        mView?.onMeaningsReady(response.body()?.toList()!!)
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