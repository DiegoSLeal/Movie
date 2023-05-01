package com.movie.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.movie.R
import com.movie.databinding.FragmentMovieDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailsFragment : Fragment() {

    companion object {
        private const val ARGUMENTKEYTITLE = "title"
        private const val ARGUMENTKEYRELEASEDATE = "release_date"
        private const val ARGUMENTKEYOVERVIEW = "overview"
        private const val ARGUMENTKEYPOSTERPATH = "poster_path"
        private const val ARGUMENTKEYGENRE = "genre_ids"
    }

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel {
        parametersOf(
            arguments?.getIntegerArrayList(ARGUMENTKEYGENRE)
        )
    }
    private lateinit var fragmentMovieDetailsBinding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        startDataBindingViewModel()
        startDataBindingLiveData()
        startDetailsDataOnScreen()
        return fragmentMovieDetailsBinding.root
    }

    private fun startDataBindingViewModel() {
        fragmentMovieDetailsBinding.moviedetailsviewmodel = movieDetailsViewModel
    }

    private fun startDataBindingLiveData() {
        fragmentMovieDetailsBinding.lifecycleOwner = this
    }

    private fun startDetailsDataOnScreen() {
        val url = arguments?.getString(ARGUMENTKEYPOSTERPATH).toString()
        movieDetailsViewModel.loadImage(
            fragmentMovieDetailsBinding.imgCharacterDetails,
            "https://image.tmdb.org/t/p/original$url"
        )
        fragmentMovieDetailsBinding.title.text = arguments?.getString(ARGUMENTKEYTITLE).toString()
        fragmentMovieDetailsBinding.tvReleaseDate.text = "Release date: " +
            arguments?.getString(ARGUMENTKEYRELEASEDATE).toString()
        fragmentMovieDetailsBinding.tvDescriptionCharacterDetails.text =
            arguments?.getString(ARGUMENTKEYOVERVIEW).toString()
        movieDetailsViewModel.dataRoomListGenre?.observe(
            viewLifecycleOwner
        ) {
            val listGenre = it
            val filteredListGenre = mutableListOf<String>()
            for (i in listGenre) {
                filteredListGenre.add(i.name!!)
            }
            val adjustListGenre = filteredListGenre.toString()
                .replace("[", "")
                .replace("]", "")

            fragmentMovieDetailsBinding.tvMovieGenre.text = adjustListGenre
        }
    }
}
