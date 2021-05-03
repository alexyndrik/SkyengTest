package com.alexyndrik.skyengtest.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity(), IBaseView {

    protected abstract fun setUp()

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorMessage: String) {

    }

    override fun showError(errorId: Int) {

    }

}