package com.alexyndrik.skyengtest.ui.search_words

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexyndrik.skyengtest.BR
import com.alexyndrik.skyengtest.R
import com.alexyndrik.skyengtest.data.remote.model.Meaning2
import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.databinding.ListWordsItemBinding
import com.alexyndrik.skyengtest.ui.meaning_info.MeaningInfoActivity

class SearchWordsAdapter(
    private val context: Context
): RecyclerView.Adapter<SearchWordsAdapter.ViewHolder>() {

    private val words = mutableListOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListWordsItemBinding>(inflater, R.layout.list_words_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, words[position])
    }

    override fun getItemCount() = words.size

    fun setItems(newWords: List<Word>) {
        words.apply {
            clear()
            addAll(newWords)
        }
    }

    class ViewHolder(private val binding: ListWordsItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, item: Word) {
            binding.apply {
                setVariable(BR.word, item)
                root.setOnClickListener {
                    context.startActivity(Intent(context, MeaningInfoActivity::class.java).apply {
                        putExtra(MeaningInfoActivity.MEANING_ID, item.meanings[0].id)
                    })
                }
            }
        }

    }

    companion object {

        @JvmStatic
        @BindingAdapter("android:text")
        fun setMeanings(textView: TextView, meaningsCnt: Int) {
            textView.text = meaningsCnt.toString()
        }

        @JvmStatic
        @BindingAdapter("android:text")
        fun setMeanings(textView: TextView, meanings: List<Meaning2>) {
            textView.text = meanings.joinToString { meaning2 -> meaning2.translation.text }
        }

    }

}