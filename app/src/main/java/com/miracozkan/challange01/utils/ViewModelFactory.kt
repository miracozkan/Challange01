package com.miracozkan.challange01.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miracozkan.challange01.base.BaseRepository
import com.miracozkan.challange01.datalayer.repo.ProjectRepository
import com.miracozkan.challange01.vm.ProjectViewModel


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 27.11.2019 - 22:08          │
//└─────────────────────────────┘

class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProjectViewModel::class.java) -> {
                ProjectViewModel(this.repository as ProjectRepository) as T
            }
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}