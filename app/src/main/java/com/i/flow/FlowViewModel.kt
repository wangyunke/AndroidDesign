package com.i.flow

import android.util.Log
import androidx.lifecycle.ViewModel
import com.i.model.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {

    private val _stateString = MutableStateFlow("0")
    val stateString = _stateString.asStateFlow()

    private val person = Person("wang", 30)
    private val _stateObj = MutableStateFlow(person)
    val stateObj = _stateObj.asStateFlow()

    fun update(value: String){
        Log.i("flow", "FlowViewModel _stateString=${_stateString.value}")
        _stateString.value = value
    }

    fun updateCold() = flow {
        Log.i("flow", "FlowViewModel updateCold do")
        emit(9)
    }

    fun updateObj(){
        Log.i("flow", "updateObj")
        person.age=35
//        _stateObj.value=person
        _stateObj.value=Person("li", 35)
    }
}