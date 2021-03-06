package com.example.sequenia_test_work.data

import com.example.sequenia_test_work.api.FilmsApi
import com.example.sequenia_test_work.data.entities.FilmEntity
import com.example.sequenia_test_work.data.entities.FilmGenreCrossRef
import com.example.sequenia_test_work.data.entities.GenreEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: FilmsApi, private val dao: FilmsDao) :
    Repository {
    private companion object {
        private val DISPATCHER = Dispatchers.IO
        private val SCOPE = CoroutineScope(DISPATCHER)
    }

    override suspend fun getFilms(): Flow<List<FilmEntity>> =
        dao.getFilms().flowOn(DISPATCHER)


    override suspend fun getGenres(): Flow<List<GenreEntity>> =
        dao.getGenres().flowOn(DISPATCHER)


    override suspend fun getFilmById(id: Long): Flow<FilmEntity> =
        dao.getFilmById(id).flowOn(DISPATCHER)

    override suspend fun getFilmsByGenre(genre: String): Flow<List<FilmEntity>> =
        dao.getGenreWithFilms(genre).map {
            it.films
        }.flowOn(DISPATCHER)


    override suspend fun getGenresByFilm(id: Int): Flow<List<GenreEntity>> =
        dao.getFilmWithGenres(id.toLong()).map {
            it.genres
        }.flowOn(DISPATCHER)

    override suspend fun downloadFilmData() {
        val response = api.getFilmsData()
        if (response.isSuccessful) {
            val data = response.body()
            data?.let {
                data.films.forEach { filmItem ->
                    val film = FilmEntity(
                        filmItem.year,
                        filmItem.imageUrl,
                        filmItem.name,
                        filmItem.rating,
                        filmItem.description,
                        filmItem.id,
                        filmItem.localizedName
                    )
                    dao.insert(film)

                    filmItem.genres.forEach { genreItem ->
                        val genre = GenreEntity(genreItem)
                        var genreId = dao.insert(genre)

                        if (genreId == -1L) {
                            genreId = withContext(SCOPE.coroutineContext) {
                                dao.getGenreByName(genreItem).genreId
                            }
                        }

                        val crossRef = FilmGenreCrossRef(filmItem.id, genreId)
                        dao.insert(crossRef)
                    }
                }
            }
        }
    }
}