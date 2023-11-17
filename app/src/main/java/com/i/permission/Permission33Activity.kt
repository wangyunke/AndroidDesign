package com.i.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.i.designpattern.R
import com.i.designpattern.flow.FileUtils
import java.io.File

class Permission33Activity : AppCompatActivity() {
    companion object {
        const val TAG = "PermissionActivity"

        var permissions = arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission33)
        onClick()
    }

    private fun onClick() {
        findViewById<View>(R.id.requestPermission).setOnClickListener {
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
                Log.i(TAG, "grant MANAGE_EXTERNAL_STORAGE")
                val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(intent)
                return@setOnClickListener
            }*/

            if (PermissionUtils.checkPermissions(this, permissions)) {
                Log.i(TAG, "-----permission=all true---")
            } else {
                Log.i(TAG, "-----permission=start request---")
                ActivityCompat.requestPermissions(this@Permission33Activity, permissions, 1)
            }
        }

        findViewById<View>(R.id.create).setOnClickListener {
            createFile()
        }
        findViewById<View>(R.id.access).setOnClickListener {
            accessFile()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val accepted = (grantResults[3] == PackageManager.PERMISSION_GRANTED)
        Log.i(TAG, "grantResults MANAGE_EXTERNAL_STORAGE=$accepted")
    }

    private fun createFile() {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/avatar54"
        val avatarDir = File(path)
        Log.i(TAG, "MainActivity fileAvatar avatarDir=" + avatarDir.absolutePath)
        if (!avatarDir.exists()) {
            val success = avatarDir.mkdirs()
            Log.i(TAG, "MainActivity avatarDir mkdirs=$success")
        }
        val newFile = File(avatarDir, "index1.html")
        if (!newFile.exists()) {
//            boolean success=newFile.createNewFile();
            Log.i(TAG, "MainActivity newFile不存在=")
        }
        val copyRes = FileUtils.copyAssetsFile(this, "index.html", newFile.absolutePath)
        Log.i("DemoActivity", "MainActivity new copyRes=$copyRes")
    }

    private fun accessFile() {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/avatar54/index1.html"
        Log.i("DemoActivity", "accessFile path=$path")
        val value = FileUtils.readContentByPath(this, path)
        Log.i("DemoActivity", "accessFile value=$value")
    }
}