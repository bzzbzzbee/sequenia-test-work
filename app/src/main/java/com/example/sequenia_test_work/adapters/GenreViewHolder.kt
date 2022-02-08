package com.example.sequenia_test_work.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sequenia_test_work.R
import com.example.sequenia_test_work.data.entities.GenreEntity
import com.example.sequenia_test_work.utilities.CommonFunctions.capitalize

class GenreViewHolder(
    parent: ViewGroup,
    private val onClick: (GenreEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
) {
    private val genreText: TextView = itemView.findViewById(R.id.text_genre_list)

    fun bind(item: ListItem.GenreItem, genres: List<ListItem.GenreItem>) {
        if (item.isSelected) {
            genreText.setBackgroundResource(R.drawable.rounded_corner_view_selected)
        } else {
            genreText.setBackgroundResource(R.drawable.rounded_corner_view_unselected)
        }
        genreText.text = item.genreEntity.name.capitalize()

        genreText.setOnClickListener {
            genres.forEach {
                if (it.genreEntity.genreId != item.genreEntity.genreId) {
                    it.isSelected = false
                } else {
                    it.isSelected = !it.isSelected
                }
            }
            onClick(item.genreEntity)
        }
    }
}