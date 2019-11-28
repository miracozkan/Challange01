package com.miracozkan.challange01.utils

import com.miracozkan.challange01.datalayer.local.ProjectDao
import com.miracozkan.challange01.datalayer.remote.ProjectService
import com.miracozkan.challange01.datalayer.repo.MainRepository
import com.miracozkan.challange01.datalayer.repo.ProjectRepository


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 27.11.2019 - 22:09          │
//└─────────────────────────────┘

object DependencyUtil {
    fun getProjectRepository(projectService: ProjectService, projectDao: ProjectDao)
            : ProjectRepository = ProjectRepository(projectService, projectDao)

    fun getMainRepository(projectDao: ProjectDao): MainRepository =
        MainRepository(projectDao = projectDao)
}