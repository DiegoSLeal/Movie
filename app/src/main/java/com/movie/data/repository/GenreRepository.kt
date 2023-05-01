package com.movie.data.repository

import com.movie.data.db.dao.GenreDao
import com.movie.data.model.GenreModel
import com.movie.data.remote.service.GenreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GenreRepository(
    private val genreDao: GenreDao,
    private val genreService: GenreService
) : GenreRepositoryInterface {

    private fun all(): GenreModel? {
        return genreDao.all()
    }

    override fun listGenre(listGenre: List<Int>?): Flow<List<GenreModel>> {
        return genreDao.listGenre(listGenre)
    }

    private fun insertMovie(genreModel: List<GenreModel>?) {
        genreDao.insertGenre(genreModel)
    }

    private fun updateMovie(genreModel: List<GenreModel>?) {
        genreDao.updateGenre(genreModel)
    }

    override suspend fun startDownloadApiGenre() {

        withContext(Dispatchers.IO) {
            val genreMovie: MutableList<GenreModel>? =
                genreService.getListGenre().genres as MutableList<GenreModel>?
            if (all() == null) {
                insertMovie(genreMovie)
            } else {
                updateMovie(genreMovie)
            }
        }
    }
}
