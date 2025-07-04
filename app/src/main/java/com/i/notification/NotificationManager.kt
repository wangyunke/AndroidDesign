package com.i.notification

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.i.MyApplication
import com.i.designpattern.R
import com.i.utils.LogUtil
import com.i.utils.Utils

object NotificationManager {
    private const val TAG = "NotificationManager"
    private const val NOTIFY_CHANNEL = "notify_channel"
    private const val NOTIFY_CHANNEL_ID = "notify_channel_id"
    private const val NOTIFY_ID = 0

    private val app: Application by lazy { MyApplication.application }
    private val ncManager by lazy {
        app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val ncEventBuilder by lazy {
        createNotifyBuilder(true)
    }

    private val ncProcessBuilder by lazy {
        createNotifyBuilder(false)
    }

    init {
        val channel = NotificationChannel(
            NOTIFY_CHANNEL_ID,
            NOTIFY_CHANNEL,
            NotificationManager.IMPORTANCE_HIGH
        )
        ncManager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    fun notifyMessage(content: String) {
        val enable = enableNotification()
        LogUtil.d(TAG, "enableNotification=$enable")
        if (!enable) {
            return
        }

        ncEventBuilder
            .setContentTitle("资源更新")
            .setContentText(content)

       NotificationManagerCompat.from(app).notify(NOTIFY_ID, ncEventBuilder.build())
    }

    @SuppressLint("MissingPermission")
    fun updateProcess(title: String, process: Int) {
        ncProcessBuilder
            .setContentTitle(title)
            .setProgress(100, process, false)
        NotificationManagerCompat.from(app).notify(NOTIFY_ID, ncProcessBuilder.build())
    }

    private fun enableNotification(): Boolean {
        val enableNotification = NotificationManagerCompat.from(app).areNotificationsEnabled()
        val grantPerm = Utils.isPermissionGranted(app, Manifest.permission.POST_NOTIFICATIONS)
        LogUtil.d(TAG, "enableNotification=$enableNotification, grantPerm=$grantPerm")
        return enableNotification && grantPerm
    }

    @SuppressLint("RemoteViewLayout")
    private fun createNotifyBuilder(cancel: Boolean): NotificationCompat.Builder {
        val pendingIntent = getNoticeIntent(app)
//        val remoteViews = RemoteViews(app.packageName, R.layout.layout_ota_notification_process).apply {
//            setTextViewText(R.id.title, "Resource downloading")
//        }

        return NotificationCompat.Builder(app, NOTIFY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(cancel)
//            .setCustomHeadsUpContentView(remoteViews)
    }

    private fun getNoticeIntent(context: Context): PendingIntent? {
        val intent = Intent().apply {
            component = ComponentName(context, "")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}