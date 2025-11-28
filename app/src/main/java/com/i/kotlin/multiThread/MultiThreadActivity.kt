package com.i.kotlin.multiThread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.i.designpattern.databinding.ActivityMultiThreadBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MultiThreadActivity: AppCompatActivity(){

    private lateinit var binding: ActivityMultiThreadBinding
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    @Volatile
    private var notifyProgress = 0
    private val mutex = Mutex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener { startTask() }
    }

    private fun startTask() {
        scope.launch {
            println("-------startTask=${Thread.currentThread().name}")
//            notifyProcess(20)
            notifyProcess(60)

            println("-------delay before")
            delay(40000)
            println("-------delay after")

            notifyProcess(100)
        }
    }

    private fun notifyProcess(maxVal: Int) {
        scope.launch {
            println("-------mutex-$maxVal before")
            mutex.withLock {
                println("-------mutex-$maxVal after")
                println("-------notifyProcess-$maxVal=${Thread.currentThread().name}")
                while (notifyProgress < maxVal) {
                    delay(1000)
                    notifyProgress += 1
                    println("notifyProgress-$maxVal=${notifyProgress}")
                }
            }
        }
    }
}