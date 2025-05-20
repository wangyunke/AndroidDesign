package com.i.download

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.i.designpattern.databinding.ActivityDownloadBinding
import com.liulishuo.okdownload.DownloadTask
import com.liulishuo.okdownload.SpeedCalculator
import com.liulishuo.okdownload.StatusUtil
import com.liulishuo.okdownload.core.Util
import com.liulishuo.okdownload.core.breakpoint.BlockInfo
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo
import com.liulishuo.okdownload.core.cause.EndCause
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend


class DownloadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDownloadBinding
    private val parentPath by lazy { filesDir }
    private lateinit var progressBar: ProgressBar
    private var totalLength: Long = 0
    private var readableTotalLength: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDownloadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.process

        init()
        onClick()
    }

    private fun init(){
        val info = StatusUtil.getCurrentInfo(DOWNLOAD_URL, parentPath.toString(), FILENAME)

        if (info != null) {
            val percent = info.getTotalOffset().toFloat() / info.getTotalLength() * 100
            Log.i(TAG, "【当前进度】$percent%")
            progressBar.setMax(info.getTotalLength().toInt())
            progressBar.progress = info.getTotalOffset().toInt()
        } else {
            Log.i(TAG, "【任务不存在】")
        }
    }

    private fun onClick() {
        val task: DownloadTask = DownloadTask.Builder(DOWNLOAD_URL, filesDir)
            .setFilename(FILENAME)
            .setMinIntervalMillisCallbackProcess(100) // 下载进度回调的间隔时间（毫秒）
            .setPassIfAlreadyCompleted(false) // 任务过去已完成是否要重新下载
            .build()

        binding.downloadStart.setOnClickListener {
            //异步执行任务
            task.enqueue(object: DownloadListener4WithSpeed() {
                override fun taskStart(task: DownloadTask) {
                    Log.i(TAG, "【1、taskStart】")
                }

                override fun connectStart(
                    task: DownloadTask,
                    blockIndex: Int,
                    requestHeaderFields: MutableMap<String, MutableList<String>>
                ) {
                    Log.i(TAG, "【2、connectStart】")
                }

                override fun connectEnd(
                    task: DownloadTask,
                    blockIndex: Int,
                    responseCode: Int,
                    responseHeaderFields: MutableMap<String, MutableList<String>>
                ) {
                    Log.i(TAG, "【3、connectEnd】responseCode=$responseCode")
                }

                override fun taskEnd(
                    task: DownloadTask,
                    cause: EndCause,
                    realCause: java.lang.Exception?,
                    taskSpeed: SpeedCalculator
                ) {
                }

                override fun infoReady(
                    task: DownloadTask,
                    info: BreakpointInfo,
                    fromBreakpoint: Boolean,
                    model: Listener4SpeedAssistExtend.Listener4SpeedModel
                ) {
                    totalLength = info.getTotalLength()
                    readableTotalLength = Util.humanReadableBytes(totalLength, true);
                    Log.i(TAG,
                        "【2、infoReady】当前进度 ${info.getTotalOffset().toFloat() / totalLength * 100}%，$info")
                    progressBar.setMax(totalLength.toInt())
                }

                override fun progressBlock(
                    task: DownloadTask,
                    blockIndex: Int,
                    currentBlockOffset: Long,
                    blockSpeed: SpeedCalculator
                ) {
                }

                override fun progress(
                    task: DownloadTask,
                    currentOffset: Long,
                    taskSpeed: SpeedCalculator
                ) {
                    val readableOffset = Util.humanReadableBytes(currentOffset, true)
                    val speed = taskSpeed.speed()
                    val percent: Float = currentOffset.toFloat() / totalLength * 100
                    Log.i(
                        TAG,
                        "【6、progress】$currentOffset[$readableOffset/$readableTotalLength]，速度：$speed，进度：$percent%"
                    )
                    progressBar.progress = currentOffset.toInt()
                }

                override fun blockEnd(
                    task: DownloadTask,
                    blockIndex: Int,
                    info: BlockInfo?,
                    blockSpeed: SpeedCalculator
                ) {
                    Log.i(TAG, "【blockEnd】")
                }

            })
            /*task.enqueue(object: DownloadListener{
                override fun taskStart(task: DownloadTask) {
                    Log.i(TAG, "taskStart")
                }

                override fun connectTrialStart(
                    task: DownloadTask,
                    requestHeaderFields: MutableMap<String, MutableList<String>>
                ) {
                }

                override fun connectTrialEnd(
                    task: DownloadTask,
                    responseCode: Int,
                    responseHeaderFields: MutableMap<String, MutableList<String>>
                ) {
                }

                override fun downloadFromBeginning(
                    task: DownloadTask,
                    info: BreakpointInfo,
                    cause: ResumeFailedCause
                ) {
                }

                override fun downloadFromBreakpoint(task: DownloadTask, info: BreakpointInfo) {
                    val block = info.getBlock(0)
                    Log.i(TAG, "downloadFromBreakpoint BreakpointInfo=${block.currentOffset}, ${block.startOffset}, ${block.contentLength}")
                }

                override fun connectStart(
                    task: DownloadTask,
                    blockIndex: Int,
                    requestHeaderFields: MutableMap<String, MutableList<String>>
                ) {
                }

                override fun connectEnd(
                    task: DownloadTask,
                    blockIndex: Int,
                    responseCode: Int,
                    responseHeaderFields: MutableMap<String, MutableList<String>>
                ) {
                }

                override fun fetchStart(task: DownloadTask, blockIndex: Int, contentLength: Long) {
                }

                override fun fetchProgress(
                    task: DownloadTask,
                    blockIndex: Int,
                    increaseBytes: Long
                ) {
                    Log.i(TAG, "fetchProgress blockIndex=$blockIndex, increaseBytes=$increaseBytes, task=${task.info?.getBlock(0)?.currentOffset}")
                }

                override fun fetchEnd(task: DownloadTask, blockIndex: Int, contentLength: Long) {
                }

                override fun taskEnd(task: DownloadTask, cause: EndCause, realCause: Exception?) {
                }

            })*/

        }

        binding.downloadPause.setOnClickListener {
            task.cancel()
        }
    }

    companion object {
        private const val TAG = "Download:Operation"
        private const val DOWNLOAD_URL = "https://cdn.llscdn.com/yy/files/xs8qmxn8-lls-LLS-5.8-800-20171207-111607.apk"
        private const val FILENAME = "update.zip"
    }

}