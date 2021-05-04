package com.alexyndrik.skyengtest.ui.base

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity(), IBaseView {

    protected var progressBar: ProgressBar? = null

    protected abstract fun setUp()

    override fun showLoading() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar?.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {

    }

    override fun showError(errorId: Int) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        hideLoading()
    }

}