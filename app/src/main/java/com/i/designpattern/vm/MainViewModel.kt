package com.i.designpattern.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val mListData: MutableLiveData<List<String>> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
    }

}