package com.example.sequenia_test_work.api

import com.example.sequenia_test_work.api.pojo.Root
import retrofit2.Response
import retrofit2.http.GET

interface FilmsApi {
    @GET("films.json")
    suspend fun getFilmsData() : Response<Root>
}