package com.movie.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.movie.data.model.MovieModel

class SearchMovieTitleUtil: SearchMovieTitleUtilInterface {
    override fun filterList(
        list: LiveData<List<MovieModel>>?,
        searchQuery: LiveData<CharSequence>
    ): LiveData<List<MovieModel>> =
        Transformations.switchMap(searchQuery) {
            list?.map { list ->
                searchQuery.value?.let {
                    list.filter { galleryShopsServices ->
                        val query = searchQuery.value.toString()
                        with(galleryShopsServices) {
                            applyQuery(query)
                        }
                    }
                }
            }
        }

    private fun MovieModel.applyQuery(query: String) =
        original_title?.contains(query, true) == true
}
