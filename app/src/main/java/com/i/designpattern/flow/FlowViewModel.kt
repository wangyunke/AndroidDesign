package com.i.designpattern.flow

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {

    private val _stateString = MutableStateFlow("0")
    val stateString = _stateString.asStateFlow()

    fun update(value: String){
        Log.i("flow", "FlowViewModel _stateString=${_stateString.value}")
        _stateString.value = value
    }

    fun updateCold() = flow {
        Log.i("flow", "FlowViewModel updateCold do")
        emit(9)
    }
}