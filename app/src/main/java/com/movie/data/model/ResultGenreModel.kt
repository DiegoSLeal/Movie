package com.movie.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ResultGenreModel(
    var genres: List<GenreModel>?
)

@Entity(tableName = "genremodel")
data class GenreModel(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "name") var name: String?
)