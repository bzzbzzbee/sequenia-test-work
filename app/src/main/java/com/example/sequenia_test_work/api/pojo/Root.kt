package com.example.sequenia_test_work.api.pojo

import com.google.gson.annotations.SerializedName

data class Root(

    @field:SerializedName("films")
    val films: List<FilmsItem>
)

data class FilmsItem(

    @field:SerializedName("year")
    val year: Int,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("genres")
    val genres: List<String>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("localized_name")
    val localizedName: String
)
