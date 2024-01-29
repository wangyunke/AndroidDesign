package com.i.voice

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i.lib_network.HttpRequestHelper
import com.i.network.RequestApi
import com.i.utils.FileUtils
import com.i.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream

class AudioViewModel : ViewModel() {
    companion object {
        const val TAG = "SceneViewModel"
    }

    private val player: MediaPlayer by lazy { MediaPlayer() }
    private val apiService =
        HttpRequestHelper.Builder().setBaseUrl("https://downsc.chinaz.net").build().getApi(
            RequestApi::class.java
        )

    fun download(url: String?, outputFile: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!FileUtils.createFile(outputFile)) {
                LogUtil.d(TAG, "url=$url, create outputFile=$outputFile fail")
                return@launch
            }

            LogUtil.d(
                TAG,
                "downloading=${url}, outputFile=$outputFile, thread=${Thread.currentThread().name}"
            )
            try {
                val response = apiService.download(url!!).execute()
                if (response.isSuccessful) {
                    val input = response.body()?.byteStream()
                    val output = FileOutputStream(File(outputFile))

                    val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                    val buffInput = BufferedInputStream(input, DEFAULT_BUFFER_SIZE)
                    var readLength: Int
                    while (buffInput.read(buffer, 0, DEFAULT_BUFFER_SIZE)
                            .also { readLength = it } != -1
                    ) {
                        output.write(buffer, 0, readLength)
                    }

                    buffInput.close()
                    output.close()
                    input?.close()
                    LogUtil.d(TAG, "download success")
                } else {
                    LogUtil.d(TAG, "download fail---")
                }
            } catch (e: Exception) {
                LogUtil.e(TAG, "download", e)
            }
        }
    }

    fun play(path: String) {
        LogUtil.d(TAG, "play=$path")
        player.setDataSource(path)
        player.isLooping = true
        player.prepare()
        player.start()
    }

    fun release() {
        player.release()
    }

}