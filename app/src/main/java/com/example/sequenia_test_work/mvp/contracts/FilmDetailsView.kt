package com.example.sequenia_test_work.mvp.contracts

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FilmDetailsView : MvpView {
    fun getFilmData()
}