package com.miracozkan.challange01.datalayer.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.miracozkan.challange01.base.BaseRepository
import com.miracozkan.challange01.datalayer.local.ProjectDao
import com.miracozkan.challange01.datalayer.model.ApiResponse


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 28.11.2019 - 17:31          │
//└─────────────────────────────┘

class MainRepository(private val projectDao: ProjectDao) : BaseRepository() {

    var listApiResponse: LiveData<PagedList<ApiResponse>>? = null
    var filterTextAll = MutableLiveData<String>("")

    init {
        initPaging()
    }

    private fun initPaging() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .build()
        listApiResponse =
            Transformations.switchMap<String, PagedList<ApiResponse>>(filterTextAll) { input: String? ->
                if (input == null || input == "" || input == "%%") {
                    Log.e("Here", "Hereeeee")
                    return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                        projectDao.getDataWithoutOrder(), config
                    ).build()
                } else {
                    Log.e("Hereeeee", "Hereeeee")
                    return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                        projectDao.loadAllByName(input), config
                    ).build()
                }
            }
    }

    fun setNewPaging(text: String) {
        filterTextAll.postValue("%$text%")
        initPaging()
    }
}