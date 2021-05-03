package com.alexyndrik.skyengtest.ui.meaning_info

import android.os.Bundle
import com.alexyndrik.skyengtest.R
import com.alexyndrik.skyengtest.WordsApp
import com.alexyndrik.skyengtest.data.remote.model.Meaning
import com.alexyndrik.skyengtest.databinding.ActivityMeaningInfoBinding
import com.alexyndrik.skyengtest.ui.base.BaseActivity
import com.bumptech.glide.Glide
import javax.inject.Inject

class MeaningInfoActivity: BaseActivity(), MeaningInfoContract.View {

    private val TAG = MeaningInfoActivity::class.java.simpleName

    @Inject
    lateinit var mPresenter: MeaningInfoPresenter

    companion object {
        const val MEANING_ID = "meaning_id"
    }

    init {
        WordsApp.mWordsComponent?.inject(this)
        mPresenter.attachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meaning_info)
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
            val binding = ActivityMeaningInfoBinding.inflate(layoutInflater)
            binding.meaning = items[0]
            setContentView(binding.root)

            val imageUrl = String.format(getString(R.string.image_url_template), items[0].images[0].url)
            Glide.with(this)
                .load(String.format(imageUrl.substring(0, imageUrl.indexOf("?"))))
                .into(binding.wordImage)
        }
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

}