package com.miracozkan.challange01.utils

import com.miracozkan.challange01.datalayer.remote.ProjectService
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
    fun getProjectRepository(projectService: ProjectService)
            : ProjectRepository = ProjectRepository(projectService)
}