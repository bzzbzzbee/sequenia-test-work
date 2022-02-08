package com.example.sequenia_test_work.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity

sealed class ListItem {
    class FilmItem(val filmPair: Pair<FilmEntity, FilmEntity?>) : ListItem()
    class GenreItem(val genreEntity: GenreEntity, var isSelected: Boolean = false) : ListItem()
    class TitleItem(val title: String) : ListItem()
}

class MainListAdapter(
    private val onClickGenre: (GenreEntity) -> Unit,
    private val onClickFilm: (FilmEntity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private companion object {
        const val FILM_VIEW_TYPE = 0
        const val GENRE_VIEW_TYPE = 1
        const val TITLE_VIEW_TYPE = 2
    }

    var dataTitle: List<ListItem> = emptyList()

    var dataGenre: List<ListItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var dataFilm: List<ListItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            FILM_VIEW_TYPE -> FilmViewHolder(parent, onClickFilm)
            GENRE_VIEW_TYPE -> GenreViewHolder(parent, onClickGenre)
            TITLE_VIEW_TYPE -> TitleViewHolder(parent)
            else -> throw IllegalArgumentException("Wrong viewType: $viewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = handlePosition(position)
        when (holder) {
            is FilmViewHolder -> holder.bind(item as ListItem.FilmItem)
            is GenreViewHolder -> holder.bind(
                item as ListItem.GenreItem,
                dataGenre as List<ListItem.GenreItem>
            )
            is TitleViewHolder -> holder.bind(item as ListItem.TitleItem)
        }
    }

    override fun getItemCount(): Int = dataTitle.size + dataGenre.size + dataFilm.size - 1

    override fun getItemViewType(position: Int): Int =
        when (handlePosition(position)) {
            is ListItem.FilmItem -> FILM_VIEW_TYPE
            is ListItem.GenreItem -> GENRE_VIEW_TYPE
            is ListItem.TitleItem -> TITLE_VIEW_TYPE
        }

    private fun handlePosition(position: Int) =
        if (position == 0) {
            dataTitle[0] as ListItem.TitleItem
        } else if (position == dataGenre.size) {
            dataTitle[1] as ListItem.TitleItem
        } else if (position > 0 && position < dataGenre.size) {
            dataGenre[position - 1] as ListItem.GenreItem
        } else {
            dataFilm[position - dataGenre.size - 1] as ListItem.FilmItem
        }
}