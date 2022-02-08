package com.example.sequenia_test_work.mvp.contracts

import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FilmListView : MvpView {
    fun onGenreClick(genre: GenreEntity)
    fun onFilmClick(film: FilmEntity)
}