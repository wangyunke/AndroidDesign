package com.i.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.i.designpattern.databinding.ActivityFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FlowActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var mBinding: ActivityFlowBinding
    private lateinit var viewModel: FlowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(mBinding.getRoot())

        viewModel = ViewModelProvider(this)[FlowViewModel::class.java]

        mBinding.cold.setOnClickListener {
            val flow = viewModel.updateCold()
            Log.i("flow", "FlowActivity flow invoke")

            lifecycleScope.launch {
                flow.collect {
                    Log.i("flow", "FlowActivity flow end")
                }
            }
        }

        mBinding.hot1.setOnClickListener {
            lifecycleScope.launch {
                viewModel.stateString.collect {
                    Log.i("flow", "FlowActivity hot1=${it}")
                }
            }
        }
        mBinding.hot2.setOnClickListener {
            lifecycleScope.launch {
                viewModel.stateString.collect {
                    Log.i("flow", "FlowActivity hot2=${it}")
                }
            }
        }

        mBinding.update1.setOnClickListener {
            viewModel.update("1")
        }
        mBinding.update2.setOnClickListener {
            viewModel.update("2")
        }

        lifecycleScope.launch {
            viewModel.stateObj.collect {
                Log.i("flow", "collect stateObj=${it.name}, ${it.age}")
            }
        }
        mBinding.updateObj.setOnClickListener {
            viewModel.updateObj()
        }

        mBinding.combine.setOnClickListener {
            runBlocking { combine()  }
        }

    }

    private suspend fun combine(){
        val flow1= flow {
            val data = fetchData1()
            println(data)
            emit(data)
        }
        val flow2= flow {
            val data = fetchData2()
            println(data)
            emit(data)
        }

        val resultFlow = flow2.combine(flow1){ s1: String, s2: String ->
            println("combine")
            "combine=$s1, $s2"
        }
        resultFlow.collect {
            println("collect: $it")
        }
    }

    private suspend fun fetchData1(): String {
        delay(1000)
        return "fetchData1"
    }

    private suspend fun fetchData2(): String {
        delay(5000)
        return "fetchData2"
    }

}