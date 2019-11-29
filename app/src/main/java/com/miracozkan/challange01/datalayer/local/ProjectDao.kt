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

    @Query("SELECT * FROM ApiResponse WHERE CAST(numBackers AS INT) >= :bankersSize")
    fun getDataWithoutOrder(bankersSize: Int): DataSource.Factory<Int, ApiResponse>

    @Query("SELECT * FROM ApiResponse WHERE CAST(numBackers AS INT) >= :bankersSize AND title LIKE  :text")
    fun loadAllByName(text: String, bankersSize: Int): DataSource.Factory<Int, ApiResponse>

    @Query("SELECT * FROM ApiResponse WHERE CAST(numBackers AS INT) >= :bankersSize AND title LIKE :text  ORDER BY title ASC")
    fun getDataWithAlphabet(text: String, bankersSize: Int): DataSource.Factory<Int, ApiResponse>

    @Query("SELECT MAX(CAST(numBackers AS INT)) FROM ApiResponse")
    suspend fun getMaxValue(): Int

}