package com.alexyndrik.skyengtest.ui.search_words

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexyndrik.skyengtest.R
import com.alexyndrik.skyengtest.data.remote.model.Word
import com.alexyndrik.skyengtest.ui.meaning_info.MeaningInfoActivity

class SearchWordsAdapter(
    private val context: Context,
    private val words: List<Word> = listOf()
): RecyclerView.Adapter<SearchWordsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_words_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, words[position])
    }

    override fun getItemCount() = words.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, item: Word) {
            itemView.findViewById<TextView>(R.id.meanings_cnt).text = item.meanings.size.toString()
            itemView.findViewById<TextView>(R.id.word).text = item.text
            itemView.findViewById<TextView>(R.id.meanings).text = item.meanings.joinToString { meaning2 -> meaning2.translation.text }

            itemView.setOnClickListener {
                context.startActivity(Intent(context, MeaningInfoActivity::class.java).apply {
                    putExtra(MeaningInfoActivity.MEANING_ID, item.meanings[0].id)
                })
            }
        }

    }

}