package com.example.sequenia_test_work.mvp.presenters

import com.example.sequenia_test_work.data.Repository
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.mvp.contracts.FilmDetailsView
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class FilmDetailsPresenter @Inject constructor(private val repository: Repository) :
    MvpPresenter<FilmDetailsView>() {

    private val _film = MutableStateFlow<FilmState>(FilmState.Loading)
    val film: StateFlow<FilmState>
        get() = _film.asStateFlow()

    fun initFilm(id: Long) {
        presenterScope.launch {
            try {
                repository.getFilmById(id).collect {
                    _film.emit(FilmState.Success(it))
                }
            } catch (t: Throwable) {
                _film.emit(FilmState.Error(t))
            }
        }
    }
}


sealed class FilmState {
    data class Success(val film: FilmEntity) : FilmState()
    object Loading : FilmState()
    data class Error(val exception: Throwable) : FilmState()
}