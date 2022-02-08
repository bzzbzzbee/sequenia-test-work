package com.example.sequenia_test_work.mvp.presenters

import androidx.navigation.NavController
import com.example.sequenia_test_work.data.Repository
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity
import com.example.sequenia_test_work.mvp.contracts.FilmListView
import com.example.sequenia_test_work.ui.fragments.FragmentFilmsListDirections
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class FilmListPresenter @Inject constructor(private val repository: Repository) :
    MvpPresenter<FilmListView>() {

    companion object {
        const val NO_SELECTION = ""
    }

    private val _genres = MutableStateFlow<GenresListState>(GenresListState.Loading)
    val genres: StateFlow<GenresListState>
        get() = _genres.asStateFlow()

    private var _selection = NO_SELECTION
    val selection
        get() = _selection

    private val _films = MutableStateFlow<FilmListState>(FilmListState.Loading)
    val films: StateFlow<FilmListState>
        get() = _films.asStateFlow()

    init {
        getGenres()
        getFilms()
    }

    private fun getGenres() {
        presenterScope.launch {
            try {
                repository.getGenres().collect {
                    _genres.emit(GenresListState.Success(it))
                }
            } catch (t: Throwable) {
                _genres.emit(GenresListState.Error(t))
            }
        }
    }

    private fun getFilms() {
        presenterScope.launch {
            try {
                repository.getFilms().collect {
                    _films.emit(FilmListState.Success(it))
                }
            } catch (t: Throwable) {
                _films.emit(FilmListState.Error(t))
            }
        }
    }

    fun onGenreClick(genre: GenreEntity) {
        presenterScope.launch {
            if (_selection == genre.name) {
                _selection = NO_SELECTION
                getFilms()
            } else {
                _selection = genre.name
                updateFilmsByGenre(genre.name)
            }
        }
    }

    private suspend fun updateFilmsByGenre(genre: String) {
        try {
            repository.getFilmsByGenre(genre).collect {
                _films.emit(FilmListState.Success(it))
            }
        } catch (t: Throwable) {
            _films.emit(FilmListState.Error(t))
        }
    }

    fun onFilmClick(film: FilmEntity, navController: NavController) {
        val direction =
            FragmentFilmsListDirections.actionFragmentFilmsListToFilmDetailsFragment(film.filmId)
        navController.navigate(direction)
    }
}

sealed class GenresListState {
    data class Success(val genres: List<GenreEntity>) : GenresListState()
    object Loading : GenresListState()
    data class Error(val exception: Throwable) : GenresListState()
}

sealed class FilmListState {
    data class Success(val films: List<FilmEntity>) : FilmListState()
    object Loading : FilmListState()
    data class Error(val exception: Throwable) : FilmListState()
}