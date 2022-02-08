package com.example.sequenia_test_work.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.sequenia_test_work.R
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.databinding.FragmentFilmDetailsBinding
import com.example.sequenia_test_work.mvp.contracts.FilmDetailsView
import com.example.sequenia_test_work.mvp.presenters.FilmDetailsPresenter
import com.example.sequenia_test_work.mvp.presenters.FilmState
import com.example.sequenia_test_work.utilities.CommonFunctions
import com.example.sequenia_test_work.utilities.CommonFunctions.collectLatestLifecycleFlow
import com.example.sequenia_test_work.utilities.CommonFunctions.round
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import javax.inject.Inject
import javax.inject.Provider
import moxy.ktx.moxyPresenter

@AndroidEntryPoint
class FilmDetailsFragment : MvpAppCompatFragment(), FilmDetailsView {
    private companion object {
        const val UNKNOWN_FILM_ID = -1L
    }

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: FilmDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var presenterProvider: Provider<FilmDetailsPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        if (args.id != UNKNOWN_FILM_ID) {
            presenter.initFilm(args.id)
            getFilmData()
        } else {
            CommonFunctions.showToast(requireContext(), R.string.unknown_error)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getFilmData() {
        collectLatestLifecycleFlow(presenter.film) { film ->
            when (film) {
                is FilmState.Success -> {
                    setFilmDetails(film.film)
                }

                is FilmState.Loading -> CommonFunctions.showToast(
                    requireContext(),
                    getString(R.string.loading)
                )

                is FilmState.Error -> CommonFunctions.showToast(
                    requireContext(),
                    film.exception.localizedMessage
                )
            }
        }
    }

    private fun setFilmDetails(film: FilmEntity) {
        if (!film.imageUrl.isNullOrEmpty()) {
            Picasso.get().load(film.imageUrl).into(binding.imgFilm)
        }
        binding.textName.text = getString(R.string.film_name, film.name)
        binding.textTitle.text = getString(R.string.film_title, film.localizedName)
        binding.textYear.text = getString(R.string.film_year, film.year)
        binding.textRating.text = getString(R.string.film_rating, film.rating.round(1).toString())
        binding.textDesc.text = if (film.description.isNullOrEmpty()) {
            getString(R.string.there_is_no_film_desc)
        } else {
            film.description
        }

    }
}