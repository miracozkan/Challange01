package com.miracozkan.challange01.datalayer.repo

import com.miracozkan.challange01.base.BaseRepository
import com.miracozkan.challange01.datalayer.local.ProjectDao
import com.miracozkan.challange01.datalayer.model.ApiResponse
import com.miracozkan.challange01.datalayer.remote.ProjectService
import com.miracozkan.challange01.utils.Result
import com.miracozkan.challange01.utils.Status
import com.miracozkan.challange01.utils.Status.*


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 27.11.2019 - 22:08          │
//└─────────────────────────────┘

class ProjectRepository(
    private val projectService: ProjectService,
    private val projectDao: ProjectDao
) :
    BaseRepository() {

    private suspend fun fetchDataFromRemote(): Result<List<ApiResponse>> {
        return getResult { projectService.getData() }
    }

    suspend fun saveDataToDB(): Status {
        return when (fetchDataFromRemote().status) {
            SUCCESS -> {
                fetchDataFromRemote().data?.forEach { _apiResponse ->
                    projectDao.insertAllData(_apiResponse)
                }
                SUCCESS
            }
            ERROR -> {
                ERROR
            }
            LOADING -> {
                LOADING
            }
        }
    }
}