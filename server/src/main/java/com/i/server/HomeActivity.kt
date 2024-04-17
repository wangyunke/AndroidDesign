package com.i.server

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.i.server.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"

    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.
        getRoot())
    }

}