package com.alexyndrik.skyengtest.ui.base

interface IBasePresenter<V : IBaseView> {

    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean

}