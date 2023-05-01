package com.movie.data.remote.service

import com.movie.data.model.ResultGenreModel
import retrofit2.http.GET
import retrofit2.http.Header

interface GenreService {

    @GET("genre/movie/list?api_key=9b12fcdacae8ee031e6cf51865b2c950")
    suspend fun getListGenre(
        @Header("Content-Type") content: String = "application/json",
    ): ResultGenreModel
}