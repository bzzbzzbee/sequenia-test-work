package com.example.sequenia_test_work.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sequenia_test_work.R

class TitleViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
) {
    private val titleText: TextView = itemView.findViewById(R.id.text_list_title)

    fun bind(item: ListItem.TitleItem) {
        titleText.text = item.title
    }
}