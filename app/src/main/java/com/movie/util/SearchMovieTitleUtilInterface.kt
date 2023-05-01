package com.movie.util

import androidx.lifecycle.LiveData
import com.movie.data.model.MovieModel

interface SearchMovieTitleUtilInterface {

    fun filterList(
        list: LiveData<List<MovieModel>>?, searchQuery: LiveData<CharSequence>
    ): LiveData<List<MovieModel>>
}
