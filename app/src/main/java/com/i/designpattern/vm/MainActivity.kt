package com.i.designpattern.vm

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.i.designpattern.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.getRoot())

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mViewModel.mListData.observe(this, Observer {

        })

        mBinding.tv.setOnClickListener(View.OnClickListener {
            Log.i("wangzhi", "mBindin点击了")
        })


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

}