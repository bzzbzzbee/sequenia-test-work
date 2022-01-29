package com.example.sequenia_test_work.data

import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getFilms(): Flow<List<FilmEntity>>
    suspend fun getGenres(): Flow<List<GenreEntity>>
    suspend fun getFilmById(id: Long): Flow<FilmEntity>
    suspend fun getFilmsByGenre(genre: String): Flow<List<FilmEntity>>
    suspend fun getGenresByFilm(id: Int): Flow<List<GenreEntity>>
    suspend fun downloadFilmData()
}