package com.example.sequenia_test_work.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sequenia_test_work.data.FilmsDb

@Entity(
    tableName = FilmsDb.FILM_TABLE_NAME,
    indices = [Index(value = ["filmId"], unique = true)]
)
data class FilmEntity(
    val year: Int,
    val imageUrl: String?,
    val name: String,
    val rating: Double,
    val description: String?,
    @PrimaryKey val filmId: Int,
    val localizedName: String
)
