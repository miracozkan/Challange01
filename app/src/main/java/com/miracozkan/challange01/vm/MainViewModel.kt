package com.miracozkan.challange01.vm

import androidx.lifecycle.MutableLiveData
import com.miracozkan.challange01.base.BaseViewModel
import com.miracozkan.challange01.datalayer.repo.MainRepository


// Code with ❤
//┌─────────────────────────────┐
//│ Created by Mirac OZKAN      │
//│ ─────────────────────────── │
//│ mirac.ozkan123@gmail.com    │
//│ ─────────────────────────── │
//│ 28.11.2019 - 17:30          │
//└─────────────────────────────┘

class MainViewModel(private val mainRepository: MainRepository) :
    BaseViewModel() {

    private val filteredText by lazy { MutableLiveData<String>() }

    val fetchList = mainRepository.listApiResponse

    fun triggerSource(text: String) {
        filteredText.postValue(text)
        mainRepository.setNewPaging(filteredText.value ?: "")
    }
}