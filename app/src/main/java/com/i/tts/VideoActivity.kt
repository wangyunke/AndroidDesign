package com.i.tts

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.i.designpattern.R
import java.io.File

class VideoActivity : ComponentActivity() {
    private lateinit var download: Button
    private lateinit var play: Button
    private val vm by lazy { VideoViewModel() }
    private val path by lazy { this.externalCacheDir!!.path + File.separator + "music0.mp3" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        init()
    }

    private fun init() {
        download = findViewById(R.id.download)
        play = findViewById(R.id.play)

        download.setOnClickListener {
            vm.download("https://downsc.chinaz.net/Files/DownLoad/sound1/202312/y2426.mp3", path)
        }
        play.setOnClickListener {
            vm.play(path)
        }
    }

    override fun onDestroy() {
        vm.release()
        super.onDestroy()
    }
}