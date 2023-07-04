package com.i.kotlin

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.i.designpattern.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {
    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge)
//        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        extensionFun()
        launchCoroutine()
        launchInActivity()
    }

    private fun launchCoroutine() {
        findViewById<TextView>(R.id.button).setOnClickListener {
            mViewModel.request()
        }
    }

    private fun launchInActivity(){
        lifecycleScope.launch {
            while (true){
                delay(2000)
                println("{闭包操作}跟随activity生命周期而变化")
            }
        }
    }

    private fun extensionFun() {
        findViewById<TextView>(R.id.tv).scaleSize(2F)
        val value = "ai qing niao".replaceA()
        println("----$value")

        val value1 = (String::replaceA)("ai qing niao")
        println(value1)

        fun1 {null}
        fun2 { null }
        fun3 { i, s ->
            println(i)
            println(s)
        }
        fun33 { i -> println(i) }
        fun4()
    }

}