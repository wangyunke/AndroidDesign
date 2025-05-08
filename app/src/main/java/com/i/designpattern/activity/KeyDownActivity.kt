package com.i.designpattern.activity

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.i.designpattern.R

class KeyDownActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aem_wake_detect)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            //音量+按键
            KeyEvent.KEYCODE_VOLUME_UP -> {
                Log.i("KeyDownActivity", "KEYCODE_VOLUME_UP")
                return true
            }
            //音量-按键
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                Log.i("KeyDownActivity", "KEYCODE_VOLUME_DOWN")
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}