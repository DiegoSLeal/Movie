package com.movie.data.repository

import com.movie.data.model.GenreModel
import kotlinx.coroutines.flow.Flow

interface GenreRepositoryInterface {

    fun listGenre(listGenre: List<Int>?): Flow<List<GenreModel>>?
    suspend fun startDownloadApiGenre()
}