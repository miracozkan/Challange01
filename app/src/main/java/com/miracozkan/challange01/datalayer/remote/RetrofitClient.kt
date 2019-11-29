package com.miracozkan.challange01.datalayer.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 27.11.2019 - 22:00          │
//└─────────────────────────────┘

class RetrofitClient {

    private val retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val projectService = retrofit.create(ProjectService::class.java)

    fun getRetrofitClient(): ProjectService = projectService

    companion object {
        const val baseUrl = "http://starlord.hackerearth.com/"
    }

}