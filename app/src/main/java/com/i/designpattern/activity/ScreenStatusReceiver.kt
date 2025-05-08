package com.i.designpattern.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.i.utils.LogUtil

class ScreenStatusReceiver: BroadcastReceiver() {
    private val TAG = "ScreenOnOffReceiver"
    override fun onReceive(content: Context?, intent: Intent?) {
        if(Intent.ACTION_SCREEN_ON == intent?.action){
            LogUtil.i(TAG, "ScreenOnOffReceiver on")
        } else if(Intent.ACTION_SCREEN_OFF == intent?.action){
            LogUtil.i(TAG, "ScreenOnOffReceiver off")
        }
    }
}