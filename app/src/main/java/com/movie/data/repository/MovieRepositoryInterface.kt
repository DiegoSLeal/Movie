package com.movie.data.repository

import com.movie.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryInterface {

    fun listMovie(): Flow<List<MovieModel>>?
    suspend fun startDownloadApiMovie()
}