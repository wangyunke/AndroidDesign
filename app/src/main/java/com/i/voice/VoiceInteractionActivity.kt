package com.i.voice

import android.app.VoiceInteractor
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.i.designpattern.R
import java.io.File

class VoiceInteractionActivity : ComponentActivity() {
    private lateinit var startVoice: Button
    private lateinit var play: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_interaction)

        init()
    }

    private fun init() {
        startVoice = findViewById(R.id.start_voice)
        play = findViewById(R.id.play)

        startVoice.setOnClickListener {
            val bundle = Bundle().apply {
                putString("name", "Test Voice Interaction")
            }
            startLocalVoiceInteraction(bundle)
        }
        play.setOnClickListener {}
    }

    override fun onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted()
        Log.i("wang", "onLocalVoiceInteractionStarted")
        val request = testConfirmation()
        voiceInteractor.submitRequest(request)
    }

    private fun testConfirmation(): VoiceInteractor.Request {
        val prompt = VoiceInteractor.Prompt("今天天气正好，你说呢")

        return object : VoiceInteractor.ConfirmationRequest(prompt, null) {
            override fun onConfirmationResult(confirmed: Boolean, result: Bundle?) {
                Log.i("wang", "onConfirmationResult confirmed=$confirmed")
                val string = if (confirmed) "confirm" else "cancel"
                Toast.makeText(this@VoiceInteractionActivity, string, Toast.LENGTH_SHORT).show()
                stopLocalVoiceInteraction()
            }

            override fun onCancel() {
                Toast.makeText(this@VoiceInteractionActivity, "onCancel", Toast.LENGTH_SHORT).show()

                stopLocalVoiceInteraction()
            }
        }
    }
}