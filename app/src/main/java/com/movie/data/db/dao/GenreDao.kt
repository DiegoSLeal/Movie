package com.movie.data.db.dao

import androidx.room.*
import com.movie.data.model.GenreModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genremodel")
    fun all(): GenreModel?

    @Query("SELECT * FROM genremodel WHERE id IN (:genre) ORDER BY name ASC")
    fun listGenre(genre: List<Int>?): Flow<List<GenreModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGenre(genreModel: List<GenreModel>?)

    @Update
    fun updateGenre(genreModel: List<GenreModel>?)
}