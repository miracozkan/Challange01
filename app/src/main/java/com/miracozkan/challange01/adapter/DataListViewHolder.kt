package com.miracozkan.challange01.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miracozkan.challange01.R
import com.miracozkan.challange01.datalayer.model.ApiResponse


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 28.11.2019 - 16:59          │
//└─────────────────────────────┘

class DataListViewHolder(private val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_data_list, parent, false)
) {
    private val txtTittle = itemView.findViewById<TextView>(R.id.txtTittle)
    private val txtLocation = itemView.findViewById<TextView>(R.id.txtLocation)
    private val txtEndTime = itemView.findViewById<TextView>(R.id.txtEndTime)
    private val txtBankers = itemView.findViewById<TextView>(R.id.txtBankers)
    private val txtPleadged = itemView.findViewById<TextView>(R.id.txtPleadged)

    fun bind(apiResponse: ApiResponse, onItemClickListener: (ApiResponse) -> Unit) {

        txtTittle.text = apiResponse.title
        txtLocation.text = apiResponse.location
        txtEndTime.text = apiResponse.endTime
        txtBankers.text = apiResponse.numBackers
        txtPleadged.text = apiResponse.amtPledged.toString()

        itemView.setOnClickListener {
            onItemClickListener(apiResponse)
        }
    }
}