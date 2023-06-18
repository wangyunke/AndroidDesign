package com.i.kotlin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {
    private val mScope: CoroutineScope = CoroutineScope(Job()+Dispatchers.IO)

    fun request(){
        mScope.launch {
            println("launch thread==${Thread.currentThread().name}")
            execLongTimes()
        }
    }

    private suspend fun execLongTimes(){
        withContext(Dispatchers.IO) {
            println("withContext thread=${Thread.currentThread().name}")
            delay(1000)
            println("long time to exec")
        }
    }

}