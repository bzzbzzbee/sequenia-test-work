package com.example.sequenia_test_work.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sequenia_test_work.R
import com.example.sequenia_test_work.adapters.ListItem
import com.example.sequenia_test_work.adapters.MainListAdapter
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity
import com.example.sequenia_test_work.databinding.FragmentFilmsListBinding
import com.example.sequenia_test_work.mvp.contracts.FilmListView
import com.example.sequenia_test_work.mvp.presenters.FilmListPresenter
import com.example.sequenia_test_work.mvp.presenters.FilmListState
import com.example.sequenia_test_work.mvp.presenters.GenresListState
import com.example.sequenia_test_work.utilities.CommonFunctions
import com.example.sequenia_test_work.utilities.CommonFunctions.collectLatestLifecycleFlow
import com.example.sequenia_test_work.utilities.CommonFunctions.toFilmPairs
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class FragmentFilmsList : MvpAppCompatFragment(), FilmListView {
    private var _binding: FragmentFilmsListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenterProvider: Provider<FilmListPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val adapter = MainListAdapter(
            onClickGenre = this::onGenreClick,
            onClickFilm = this::onFilmClick
        ).also {
            it.dataTitle = listOf(
                ListItem.TitleItem(getString(R.string.genres_title)),
                ListItem.TitleItem(getString(R.string.films_title))
            )
        }

        collectGenres(adapter)
        collectFilms(adapter)

        binding.recyclerFilmsGenres.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onGenreClick(genre: GenreEntity) {
        presenter.onGenreClick(genre)
    }

    override fun onFilmClick(film: FilmEntity) {
        presenter.onFilmClick(film, findNavController())
    }

    private fun collectFilms(adapter: MainListAdapter) {
        collectLatestLifecycleFlow(presenter.films) { filmsState ->
            when (filmsState) {
                is FilmListState.Success -> {
                    adapter.dataFilm =
                        filmsState.films
                            .sortedBy { it.localizedName }
                            .toFilmPairs()
                            .map { pair -> ListItem.FilmItem(pair) }
                }

                is FilmListState.Loading -> CommonFunctions.showToast(
                    requireContext(),
                    getString(R.string.loading)
                )

                is FilmListState.Error -> CommonFunctions.showToast(
                    requireContext(),
                    filmsState.exception.localizedMessage
                )

            }
        }
    }

    private fun collectGenres(adapter: MainListAdapter) {
        collectLatestLifecycleFlow(presenter.genres) { genreState ->
            when (genreState) {
                is GenresListState.Success -> {
                    adapter.dataGenre = genreState.genres.map { genre ->
                        ListItem.GenreItem(
                            genre,
                            presenter.selection == genre.name
                        )
                    }
                }

                is GenresListState.Loading -> CommonFunctions.showToast(
                    requireContext(),
                    getString(R.string.loading)
                )

                is GenresListState.Error -> CommonFunctions.showToast(
                    requireContext(),
                    genreState.exception.localizedMessage
                )

            }
        }
    }
}