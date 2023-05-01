package com.movie.data.repository

import com.movie.data.db.dao.MovieDao
import com.movie.data.model.MovieModel
import com.movie.data.remote.service.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieRepository(
    private val movieDao: MovieDao,
    private val movieService: MovieService
) : MovieRepositoryInterface {

    private fun all(): MovieModel? {
        return movieDao.all()
    }

    override fun listMovie(): Flow<List<MovieModel>>? {
        return movieDao.listMovie()
    }

    private fun insertMovie(movieModel: List<MovieModel>?) {
        movieDao.insertMovie(movieModel)
    }

    private fun updateMovie(movieModel: List<MovieModel>?) {
        movieDao.updateMovie(movieModel)
    }

    override suspend fun startDownloadApiMovie() {

        withContext(Dispatchers.IO) {
            val listMovie: MutableList<MovieModel>? =
                movieService.getListMovie().results as MutableList<MovieModel>?
            if (all() == null) {
                insertMovie(listMovie)
            } else {
                updateMovie(listMovie)
            }
        }
    }
}
