package com.alexyndrik.skyengtest.ui.search_words

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.alexyndrik.skyengtest.R
import com.alexyndrik.skyengtest.data.remote.model.Meaning2
import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.databinding.ListWordsItemBinding
import com.alexyndrik.skyengtest.databinding.ListWordsItemChildBinding
import com.alexyndrik.skyengtest.ui.meaning_info.MeaningInfoActivity
import com.alexyndrik.skyengtest.util.GlideUtil

class SearchWordsAdapter(
    private val context: Context
): BaseExpandableListAdapter() {

    private val words = mutableListOf<Word>()

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        return if (words[groupPosition].meanings.size > 1) {
            val inflater = LayoutInflater.from(context)
            val binding = DataBindingUtil.inflate<ListWordsItemBinding>(inflater, R.layout.list_words_item, parent, false)
            binding.word = words[groupPosition]
            binding.isExpanded = isExpanded
            binding.root
        } else getChildView(groupPosition, 0, true, null, null).apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val item = words[groupPosition].meanings[childPosition]
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<ListWordsItemChildBinding>(inflater, R.layout.list_words_item_child, parent, false)
        binding.meaning = item
        binding.root.setOnClickListener {
            context.startActivity(Intent(context, MeaningInfoActivity::class.java).apply {
                putExtra(MeaningInfoActivity.MEANING_ID, item.id)
            })
        }
        GlideUtil.loadImage(context, item.imageUrl, binding.meaningImage)
        return binding.root
    }

    override fun getGroupCount() = words.size

    override fun getChildrenCount(groupPosition: Int) = words[groupPosition].meanings.size

    override fun getGroup(groupPosition: Int) = words[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int) = words[groupPosition].meanings[childPosition]

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun hasStableIds() = false

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    fun setItems(newWords: List<Word>) {
        words.apply {
            clear()
            addAll(newWords)
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