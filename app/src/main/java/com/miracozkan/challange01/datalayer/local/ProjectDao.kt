package com.miracozkan.challange01.datalayer.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miracozkan.challange01.datalayer.model.ApiResponse


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 28.11.2019 - 08:05          │
//└─────────────────────────────┘

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllData(apiResponse: ApiResponse)

    @Query("SELECT * FROM ApiResponse")
    fun getDataWithoutOrder(): DataSource.Factory<Int, ApiResponse>

    @Query("SELECT * FROM ApiResponse where title LIKE  :text")
    fun loadAllByName(text: String?): DataSource.Factory<Int, ApiResponse>

    @Query("SELECT * FROM ApiResponse WHERE title LIKE :text ORDER BY title ASC")
    fun getDataWithAlphabet(text: String): DataSource.Factory<Int, ApiResponse>

}