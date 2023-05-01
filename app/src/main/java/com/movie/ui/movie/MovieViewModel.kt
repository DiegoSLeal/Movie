package com.movie.ui.movie

import android.os.Bundle
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.movie.R
import com.movie.data.model.MovieModel
import com.movie.data.repository.MovieRepositoryInterface
import com.movie.util.SearchMovieTitleUtilInterface
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepositoryInterface: MovieRepositoryInterface,
    private val navController: NavController,
    searchMovieTitleUtilInterface: SearchMovieTitleUtilInterface
) : ViewModel() {

    private val _dataRoomListMovie =
        movieRepositoryInterface.listMovie()?.asLiveData() as MutableLiveData<List<MovieModel>>?
    private val dataRoomListMovie: LiveData<List<MovieModel>>?
        get() = _dataRoomListMovie

    private val _searchQuery = MutableLiveData<CharSequence>("")
    private val searchQuery: LiveData<CharSequence>
        get() = _searchQuery

    val filteredListMovie: LiveData<List<MovieModel>> =
        searchMovieTitleUtilInterface.filterList(dataRoomListMovie, searchQuery)

    fun setSearchQuery(query: CharSequence?) {
        query?.let {
            _searchQuery.value = it
        }
    }

    init {
        startDownloadApiMovie()
    }

    private fun startDownloadApiMovie() {
        viewModelScope.launch {
            movieRepositoryInterface.startDownloadApiMovie()
        }
    }

    fun startClickButtonNavigateMovieDetails(bundle: Bundle) {
        navController.navigate(R.id.action_movieFragment_to_movieDetailsFragment, bundle)
    }
}