package com.example.sequenia_test_work.data

import com.example.sequenia_test_work.api.FilmsApi
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.GenreEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: FilmsApi, private val dao: FilmsDao) : Repository {
    override suspend fun getFilms(): Flow<List<FilmEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getGenres(): Flow<List<GenreEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilmById(id: Int): Flow<FilmEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilmsByGenre(genre: String): Flow<List<FilmEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getGenresByFilm(id: Int): Flow<List<GenreEntity>> {
        TODO("Not yet implemented")
    }

    //TODO sharedPrefs is download needed?
    suspend fun downloadFilmData() {
        TODO("Download film data")
    }
}