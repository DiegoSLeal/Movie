package com.movie.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.movie.data.db.dao.GenreDao
import com.movie.data.model.GenreModel

@Database(entities = [GenreModel::class], version = 1, exportSchema = false)
abstract class GenreDatabase : RoomDatabase() {

    abstract val genreDao: GenreDao

    companion object {

        @Volatile
        private var INSTANCE: GenreDatabase? = null

        fun getInstance(context: Context): GenreDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GenreDatabase::class.java,
                    "genredatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}