package com.i.service

import android.app.Dialog
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager

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
        val params = WindowManager.LayoutParams();
        params.width = 500;
        params.height = 86;
        params.gravity = Gravity.TOP and Gravity.CENTER
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

        val float = FloatView(this)
        float.setManager(mWindowManager, params)

        mWindowManager.addView(float, params)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}