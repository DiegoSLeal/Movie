package com.movie.data.db.dao

import androidx.room.*
import com.movie.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM moviemodel")
    fun all(): MovieModel?

    @Query("SELECT * FROM moviemodel")
    fun listMovie(): Flow<List<MovieModel>>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movieModel: List<MovieModel>?)

    @Update
    fun updateMovie(movieModel: List<MovieModel>?)
}