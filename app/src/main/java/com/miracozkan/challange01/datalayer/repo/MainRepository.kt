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
    private var bankersSize: Int = -1

    init {
        initPaging()
        filterTextAll.postValue("")
    }

    suspend fun fetchMaxValue(): Int {
        return projectDao.getMaxValue()
    }

    private fun initPaging() {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .build()
        listApiResponse =
            Transformations.switchMap<String, PagedList<ApiResponse>>(filterTextAll) { input: String? ->
                if (input == null || input == "" || input == "%%") {
                    if (isAlphabetList) {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.getDataWithAlphabet("%%", bankersSize), config
                        ).build()
                    } else {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.getDataWithoutOrder(bankersSize), config
                        ).build()
                    }
                } else {
                    if (isAlphabetList) {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.getDataWithAlphabet(input, bankersSize), config
                        ).build()
                    } else {
                        return@switchMap LivePagedListBuilder<Int, ApiResponse>(
                            projectDao.loadAllByName(input, bankersSize), config
                        ).build()
                    }
                }
            }
    }

    fun setNewPaging(text: String, isAlphabet: Boolean, _bankersSize: Int) {
        filterTextAll.postValue("%$text%")
        isAlphabetList = isAlphabet
        bankersSize = _bankersSize
        initPaging()
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}