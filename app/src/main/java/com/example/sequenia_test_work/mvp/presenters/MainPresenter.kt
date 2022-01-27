package com.example.sequenia_test_work.mvp.presenters

import com.example.sequenia_test_work.data.Repository
import com.example.sequenia_test_work.mvp.contracts.MainView
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class MainPresenter @Inject constructor(private val repository: Repository) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        presenterScope.launch {
            repository.downloadFilmData()
        }
    }
}