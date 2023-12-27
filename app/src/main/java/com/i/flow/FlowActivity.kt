package com.i.flow

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.i.designpattern.databinding.ActivityFlowBinding
import com.i.designpattern.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

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
    }

}