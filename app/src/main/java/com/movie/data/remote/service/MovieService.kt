package com.movie.data.remote.service

import com.movie.data.model.ResultMovieModel
import retrofit2.http.GET
import retrofit2.http.Header

interface MovieService {

    @GET("movie/popular?api_key=9b12fcdacae8ee031e6cf51865b2c950")
    suspend fun getListMovie(
        @Header("Content-Type") content: String = "application/json",
    ): ResultMovieModel
}