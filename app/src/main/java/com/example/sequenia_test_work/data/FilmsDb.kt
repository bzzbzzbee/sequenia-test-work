package com.example.sequenia_test_work.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity

@Database(
    entities = [FilmEntity::class, GenreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FilmsDb : RoomDatabase() {
    companion object {
        const val DB_NAME = "films-db"
        const val GENRE_TABLE_NAME = "genres"
        const val FILM_TABLE_NAME = "films"
        const val FILM_GENRE_CROSSREF_TABLE_NAME = "film_genre_crossref"
    }

    abstract fun filmsDao() : FilmsDao
}