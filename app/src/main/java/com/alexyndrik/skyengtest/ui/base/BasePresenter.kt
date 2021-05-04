package com.alexyndrik.skyengtest.ui.base

import io.reactivex.disposables.Disposable

open class BasePresenter<V: IBaseView>: IBasePresenter<V> {

    protected var mView: V? = null
        private set

    protected var mDisposable : Disposable? = null

    override fun attachView(view: V) {
        this.mView = view
    }

    override fun detachView() {
        if (mView != null) mView = null
        if (mDisposable != null) mDisposable?.dispose()
    }

    override fun isViewAttached() = mView != null

    fun checkViewAttached() {
        if (!isViewAttached()) throw ViewNotAttachedException()
    }

    class ViewNotAttachedException :
        RuntimeException("Please call Presenter.attachView(view) before requesting data to the Presenter")

}