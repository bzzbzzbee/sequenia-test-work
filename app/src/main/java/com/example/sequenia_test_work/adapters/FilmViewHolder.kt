package com.example.sequenia_test_work.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sequenia_test_work.R
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity
import com.squareup.picasso.Picasso

class FilmViewHolder(
    parent: ViewGroup,
    private val onClick: (FilmEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_film_pair, parent, false)
) {
    private val firstFilmTitle: TextView = itemView.findViewById(R.id.text_first_title)
    private val firstFilmImg: ImageView = itemView.findViewById(R.id.img_first_film)
    private val firstFilmLayout: LinearLayout = itemView.findViewById(R.id.layout_first_film)

    private val secondFilmTitle: TextView = itemView.findViewById(R.id.text_second_title)
    private val secondFilmImg: ImageView = itemView.findViewById(R.id.img_second_film)
    private val secondFilmLayout: LinearLayout = itemView.findViewById(R.id.layout_second_film)

    fun bind(pair: ListItem.FilmItem) {
        val firstFilm = pair.filmPair.first
        val secondFilm = pair.filmPair.second

        setFirstFilm(firstFilm)
        setSecondFilm(secondFilm)
    }

    private fun setSecondFilm(film: FilmEntity?) {
        if (film != null) {
            secondFilmTitle.text = film.localizedName

            if (!film.imageUrl.isNullOrEmpty()) {
                Picasso.get().load(film.imageUrl).into(secondFilmImg)
            }

            secondFilmLayout.setOnClickListener {
                onClick(film)
            }
        } else {
            secondFilmImg.visibility = View.INVISIBLE
            secondFilmTitle.text = ""
            secondFilmLayout.setOnClickListener(null)
        }
    }

    private fun setFirstFilm(film: FilmEntity) {
        firstFilmTitle.text = film.localizedName

        if (!film.imageUrl.isNullOrEmpty()) {
            Picasso.get().load(film.imageUrl).into(firstFilmImg)
        }

        firstFilmLayout.setOnClickListener {
            onClick(film)
        }
    }
}