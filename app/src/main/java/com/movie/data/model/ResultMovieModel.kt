package com.movie.data.model

import androidx.room.*

data class ResultMovieModel(
    var page: Int?,
    var results: List<MovieModel>?,
    var total_pages: Int?,
    var total_results: Int?
)

@Entity(tableName = "moviemodel")
data class MovieModel(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "adult") var adult: Boolean?,
    @ColumnInfo(name = "backdrop_path") var backdrop_path: String?,
    @ColumnInfo(name = "genre_ids") val genre_ids: List<Int>?,
    @ColumnInfo(name = "original_language") var original_language: String?,
    @ColumnInfo(name = "original_title") var original_title: String?,
    @ColumnInfo(name = "overview") var overview: String?,
    @ColumnInfo(name = "popularity") var popularity: Double?,
    @ColumnInfo(name = "poster_path") var poster_path: String?,
    @ColumnInfo(name = "release_date") var release_date: String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "video") var video: Boolean?,
    @ColumnInfo(name = "vote_average") var vote_average: Double?,
    @ColumnInfo(name = "vote_count") var vote_count: Int?
)
