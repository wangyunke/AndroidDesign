package com.i.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.i.designpattern.databinding.ActivityNotificationBinding
import java.util.regex.Pattern


class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init(){
    }

    private fun onClick() {
        binding.notifyMsg.setOnClickListener {
            NotificationManager.notifyMessage("重要更新")
        }

        binding.zipFile.setOnClickListener {
        }

        binding.matchFile.setOnClickListener {

        }
    }

    companion object {
        private const val TAG = "NotificationActivity"
    }

}