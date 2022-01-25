package com.example.sequenia_test_work.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sequenia_test_work.data.FilmsDb

@Entity(
    tableName = FilmsDb.GENRE_TABLE_NAME,
    indices = [Index(value = ["genreId", "name"], unique = true)]
)
data class GenreEntity(val name: String) {
    @PrimaryKey(autoGenerate = true)
    var genreId: Int = 0
}
