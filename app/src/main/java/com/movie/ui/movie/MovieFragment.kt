package com.movie.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.R
import com.movie.databinding.FragmentMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieFragment : Fragment() {

    companion object {
        private const val ARGUMENTKEYTITLE = "title"
        private const val ARGUMENTKEYRELEASEDATE = "release_date"
        private const val ARGUMENTKEYOVERVIEW = "overview"
        private const val ARGUMENTKEYPOSTERPATH = "poster_path"
        private const val ARGUMENTKEYGENRE = "genre_ids"
    }

    private val movieViewModel: MovieViewModel by viewModel {
        parametersOf(
            findNavController()
        )
    }
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private val movieAdapter = MovieAdapter(ClickMovieDetails { moviemodel ->
        movieViewModel.startClickButtonNavigateMovieDetails(Bundle().apply {
            putString(ARGUMENTKEYTITLE, moviemodel?.title.toString())
            putString(ARGUMENTKEYRELEASEDATE, moviemodel?.release_date.toString())
            putString(ARGUMENTKEYOVERVIEW, moviemodel?.overview.toString())
            putString(ARGUMENTKEYPOSTERPATH, moviemodel?.poster_path.toString())
            putIntegerArrayList(ARGUMENTKEYGENRE, moviemodel?.genre_ids?.let { ArrayList(it) })
        })
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        startDataBindingViewModel()
        startDataBindingLiveData()
        startRecyclerView()
        startObserveListMovie()
        startSearchView()
        return fragmentMovieBinding.root
    }

    private fun startDataBindingViewModel() {
        fragmentMovieBinding.movielistviewmodel = movieViewModel
    }

    private fun startDataBindingLiveData() {
        fragmentMovieBinding.lifecycleOwner = this
    }

    private fun startRecyclerView() {
        fragmentMovieBinding.recyclerview.setHasFixedSize(true)
        fragmentMovieBinding.recyclerview.layoutManager =
            LinearLayoutManager(requireContext())
        fragmentMovieBinding.recyclerview.adapter = movieAdapter
    }

    private fun startObserveListMovie() {
        movieViewModel.filteredListMovie.observe(
            viewLifecycleOwner
        ) {
            it?.let {
                movieAdapter.submitList(it)
            }
        }
    }

    private fun startSearchView() {
        fragmentMovieBinding.searchview.setOnQueryTextListener(
            object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        movieViewModel.setSearchQuery(it)
                        startRecyclerViewScrollPositionTop()
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        movieViewModel.setSearchQuery(it)
                        startRecyclerViewScrollPositionTop()
                    }
                    return false
                }
            })
    }

    private fun startRecyclerViewScrollPositionTop() {
        fragmentMovieBinding.recyclerview.scrollToPosition(
            0
        )
    }

}
