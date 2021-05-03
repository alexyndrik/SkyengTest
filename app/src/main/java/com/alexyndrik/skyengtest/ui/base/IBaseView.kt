package com.alexyndrik.skyengtest.ui.base

import androidx.annotation.StringRes

interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(errorMessage: String)

    fun showError(@StringRes errorId: Int)

}