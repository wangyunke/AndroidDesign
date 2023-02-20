package com.i.service

import android.app.Dialog
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.*
import com.i.designpattern.R

class WindowViewService: Service() {
    private lateinit var mWindowManager: WindowManager

    override fun onCreate() {
        super.onCreate()
        mWindowManager = Dialog(this).window!!.windowManager
//        mWindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createView()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createView() {
        val params = LayoutParams();
        params.height = 500;
        params.width = 500;
        params.gravity = Gravity.TOP and Gravity.CENTER
        params.type = LayoutParams.TYPE_APPLICATION_OVERLAY
        params.flags = LayoutParams.FLAG_NOT_FOCUSABLE

        val view = LayoutInflater.from(this).inflate(R.layout.activity_knowledge, null);
        view.setOnClickListener { mWindowManager.removeView(it) }

        mWindowManager.addView(view, params)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}