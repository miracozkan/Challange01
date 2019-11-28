package com.miracozkan.challange01.datalayer.remote

import com.miracozkan.challange01.datalayer.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 27.11.2019 - 21:56          │
//└─────────────────────────────┘

interface ProjectService {
    @GET("kickstarter")
    suspend fun getData(): Response<List<ApiResponse>>
}