package com.miracozkan.challange01.datalayer.repo

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
    private var filterTextAll = MutableLiveData<String>()
    private var isAlphabetList: Boolean = false

    init {
        initPaging()
        filterTextAll.postValue("")
    }

    private fun initPaging() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .build()
        listApiResponse =
            Transformations.switchMap<String, PagedList<ApiResponse>>(filterTextAll) { input: String? ->
                if (input == null || input == "" || input == "%%") {
                    if (isAlphabetList) {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.getDataWithAlphabet("%%"), config
                        ).build()
                    } else {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.getDataWithoutOrder(), config
                        ).build()
                    }
                } else {
                    if (isAlphabetList) {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.getDataWithAlphabet(input), config
                        ).build()
                    } else {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.loadAllByName(input), config
                        ).build()
                    }
                }
            }
    }

    fun setNewPaging(text: String, isAlphabet: Boolean) {
        filterTextAll.postValue("%$text%")
        isAlphabetList = isAlphabet
        initPaging()
    }
}