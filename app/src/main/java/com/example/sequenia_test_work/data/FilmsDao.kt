package com.example.sequenia_test_work.data

import androidx.room.*
import com.example.sequenia_test_work.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films ORDER BY localizedName ASC")
    fun getFilms(): Flow<List<FilmEntity>>

    @Query("SELECT * FROM films WHERE filmId IN(:id)")
    fun getFilmById(id: Long): Flow<FilmEntity>

    @Query("SELECT * FROM genres ORDER BY name ASC")
    fun getGenres(): Flow<List<GenreEntity>>

    @Query("SELECT * FROM genres WHERE name IN(:name)")
    fun getGenreByName(name: String): GenreEntity

    @Transaction
    @Query("SELECT * FROM genres WHERE name IN(:genre)")
    fun getGenreWithFilms(genre: String): Flow<GenreWithFilms>

    @Transaction
    @Query("SELECT * FROM films WHERE filmId IN(:id)")
    fun getFilmWithGenres(id: Long): Flow<FilmWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: FilmEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(genreEntity: GenreEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(crossRef: FilmGenreCrossRef): Long
}