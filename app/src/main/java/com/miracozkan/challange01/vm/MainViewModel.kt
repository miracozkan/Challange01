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

    val filteredText by lazy { MutableLiveData<String>() }
    private val isAlphabet by lazy { MutableLiveData<Boolean>() }

    val fetchList = mainRepository.listApiResponse

    fun triggerSource(text: String, state: Boolean) {
        filteredText.postValue(text)
        isAlphabet.postValue(state)
        mainRepository.setNewPaging(text, state)
    }
}