package com.i.designpattern.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.i.designpattern.R
import com.i.designpattern.databinding.ActivityAemWakeDetectBinding
import com.i.hook.Catcher

class KeyDownActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAemWakeDetectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAemWakeDetectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
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

    @SuppressLint("ClickableViewAccessibility")
    private fun init(){
        /**
         * 顶部view设置为INVISIBLE，不影响底部view的触摸和点击事件
         */
        binding.top.setOnClickListener {
            Toast.makeText(this, "top点击了", Toast.LENGTH_LONG).show()
        }
        binding.top.visibility = View.INVISIBLE

        binding.bottom.setOnTouchListener { _, _ ->
            Toast.makeText(this, "bottom触摸了", Toast.LENGTH_LONG).show()
            false
        }
        binding.bottom.setOnClickListener {
            Toast.makeText(this, "bottom点击了", Toast.LENGTH_LONG).show()
        }
    }
}