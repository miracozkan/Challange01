package com.miracozkan.challange01.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miracozkan.challange01.base.BaseViewModel
import com.miracozkan.challange01.datalayer.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

    val getMaxValueFromDb by lazy { MutableLiveData<Int>() }
    val filteredText by lazy { MutableLiveData<String>() }
    private val isAlphabet by lazy { MutableLiveData<Boolean>() }

    init {
        fetchMaxValue()
    }

    val fetchList = mainRepository.listApiResponse

    fun triggerSource(text: String, state: Boolean, bankersSize: Int) {
        filteredText.postValue(text)
        isAlphabet.postValue(state)
        mainRepository.setNewPaging(text, state, bankersSize)
    }

    private fun fetchMaxValue() {
        viewModelScope.launch(Dispatchers.IO) {
            getMaxValueFromDb.postValue(mainRepository.fetchMaxValue())
        }
    }
}