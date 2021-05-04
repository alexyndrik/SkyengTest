package com.alexyndrik.skyengtest.ui.meaning_info

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alexyndrik.skyengtest.BR
import com.alexyndrik.skyengtest.R
import com.alexyndrik.skyengtest.WordsApp
import com.alexyndrik.skyengtest.data.remote.model.Meaning
import com.alexyndrik.skyengtest.databinding.ActivityMeaningInfoBinding
import com.alexyndrik.skyengtest.ui.base.BaseActivity
import com.alexyndrik.skyengtest.util.GlideUtil
import javax.inject.Inject

class MeaningInfoActivity: BaseActivity(), MeaningInfoContract.View {

    private val TAG = MeaningInfoActivity::class.java.simpleName

    @Inject
    lateinit var mPresenter: MeaningInfoPresenter

    lateinit var binding: ActivityMeaningInfoBinding

    companion object {
        const val MEANING_ID = "meaning_id"
    }

    init {
        WordsApp.mWordsComponent?.inject(this)
        mPresenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_meaning_info, null, false)
        setContentView(binding.root)

        setUp()

        val meaningId = intent.getStringExtra(MEANING_ID)
        if (!meaningId.isNullOrEmpty()) {
            showLoading()
            mPresenter.getMeanings(listOf(meaningId))
        }
    }

    override fun setUp() {
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun onMeaningsReady(items: List<Meaning>) {
        hideLoading()
        if (items.isNotEmpty()) {
            binding.setVariable(BR.meaning, items[0])
            GlideUtil.loadImage(this, items[0].images[0].url, binding.wordImage)
        }
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

}