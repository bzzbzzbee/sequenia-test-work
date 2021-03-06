package com.example.sequenia_test_work.data.entities

import androidx.room.*
import com.example.sequenia_test_work.data.FilmsDb

@Entity(
    tableName = FilmsDb.FILM_GENRE_CROSSREF_TABLE_NAME,
    primaryKeys = ["genreId", "filmId"],
    foreignKeys = [
        ForeignKey(
            entity = FilmEntity::class,
            parentColumns = ["filmId"],
            childColumns = ["filmId"],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["genreId"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("filmId"), Index("genreId")]
)
data class FilmGenreCrossRef(
    val filmId: Long,
    val genreId: Long
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