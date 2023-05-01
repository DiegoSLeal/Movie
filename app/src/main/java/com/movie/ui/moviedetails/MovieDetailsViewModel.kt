package com.movie.ui.moviedetails

import android.widget.ImageView
import androidx.lifecycle.*
import com.movie.data.model.GenreModel
import com.movie.data.repository.GenreRepositoryInterface
import com.movie.util.GlideUtilInterface
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val glideUtilInterface: GlideUtilInterface,
    private val genreRepositoryInterface: GenreRepositoryInterface,
    listGenre: List<Int>
) : ViewModel() {

    private val _dataRoomListGenre =
        genreRepositoryInterface.listGenre(listGenre)
            ?.asLiveData() as MutableLiveData<List<GenreModel>>?
    val dataRoomListGenre: LiveData<List<GenreModel>>?
        get() = _dataRoomListGenre

    init {
        startDownloadApiGenre()
    }

    private fun startDownloadApiGenre() {
        viewModelScope.launch {
            genreRepositoryInterface.startDownloadApiGenre()
        }
    }

    fun loadImage(imageView: ImageView, imageUrl: String) {
        glideUtilInterface.loadImage(imageView, imageUrl)
    }
}