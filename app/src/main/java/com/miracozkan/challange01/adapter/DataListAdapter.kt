package com.miracozkan.challange01.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.miracozkan.challange01.datalayer.model.ApiResponse
import com.miracozkan.challange01.utils.CustomDiffUtil


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 28.11.2019 - 17:14          │
//└─────────────────────────────┘

class DataListAdapter(
    private val onItemClickListener: (ApiResponse) -> Unit
) : PagedListAdapter<ApiResponse, DataListViewHolder>(CustomDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder =
        DataListViewHolder(parent)

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClickListener)
        }
    }
}