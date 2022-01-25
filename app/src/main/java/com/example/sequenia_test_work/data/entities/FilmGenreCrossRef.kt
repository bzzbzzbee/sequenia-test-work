package com.example.sequenia_test_work.data.entities

import androidx.room.*
import com.example.sequenia_test_work.data.FilmsDb

@Entity(
    tableName = FilmsDb.FILM_GENRE_CROSSREF_TABLE_NAME,
    primaryKeys = ["genreId", "filmId"]
)
data class FilmGenreCrossRef(
    val filmId: Int,
    val genreId: Int
)

data class FilmWithGenres(
    @Embedded val playlist: FilmEntity,
    @Relation(
        parentColumn = "filmId",
        entityColumn = "genreId",
        associateBy = Junction(FilmGenreCrossRef::class)
    )
    val genres: List<GenreEntity>
)

data class GenreWithFilms(
    @Embedded val genre: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "filmId",
        associateBy = Junction(FilmGenreCrossRef::class)
    )
    val films: List<FilmEntity>
)