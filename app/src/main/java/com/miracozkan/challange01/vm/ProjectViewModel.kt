package com.miracozkan.challange01.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miracozkan.challange01.base.BaseViewModel
import com.miracozkan.challange01.datalayer.repo.ProjectRepository
import com.miracozkan.challange01.utils.Status
import com.miracozkan.challange01.utils.Status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 27.11.2019 - 22:08          │
//└─────────────────────────────┘

class ProjectViewModel(private val projectRepository: ProjectRepository) : BaseViewModel() {

    val projectDataStatus by lazy {
        MutableLiveData<Status>()
    }

    private var projectJob: Job? = null

    init {
        setLastJob()
    }

    private fun setLastJob() {
        if (projectJob?.isActive == true) {
            return
        }
        projectJob = getProjectData()
    }

    private fun getProjectData(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            when (projectRepository.saveDataToDB()) {
                SUCCESS -> {
                    projectDataStatus.postValue(SUCCESS)
                }
                ERROR -> {
                    projectDataStatus.postValue(ERROR)
                }
                LOADING -> {
                    projectDataStatus.postValue(LOADING)
                }
            }
        }
    }
}