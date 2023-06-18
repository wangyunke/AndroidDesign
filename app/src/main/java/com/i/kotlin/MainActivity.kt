package com.i.kotlin

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.i.designpattern.R

class MainActivity: AppCompatActivity() {
    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge)
//        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        extensionFun()
        launchCoroutine()
    }

    private fun launchCoroutine() {
        findViewById<TextView>(R.id.button).setOnClickListener {
            mViewModel.request()
        }
    }

    private fun extensionFun() {
        findViewById<TextView>(R.id.tv).scaleSize(2F)
        val value = "ai qing niao".replaceA()
        println("----$value")

        val value1 = (String::replaceA)("ai qing niao")
        println(value1)
    }

}