package com.i.permission

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.i.designpattern.R
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

class ReflectActivity : ComponentActivity() {
    private val TAG = "ReflectActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        onClick()
        registerPermissionListener(this)
    }

    private fun onClick() {
        findViewById<View>(R.id.requestPermission).setOnClickListener { v: View? ->
            //没有权限，提示获取权限
            val perms = arrayOf(
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
            )
            ActivityCompat.requestPermissions(this, perms, 1)
        }
    }

    @Override
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                for (index in grantResults) {
                    val accepted = grantResults[index] == PackageManager.PERMISSION_GRANTED
                    Log.i(
                        TAG,
                        "-----permission=" + permissions[index] + ", grantResults=--" + accepted
                    )
                }
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun registerPermissionListener(context: Context) {
        val permissionService = getSystemService("permission");
        checkNotNull(permissionService)
        println("获取PermissionService成功:${permissionService}")
        val clazz = Class.forName("android.content.pm.PackageManager\$OnPermissionsChangedListener")
        val invokerHandler = InvocationHandler { proxy, method, args ->
            {
                check(method.name == "onPermissionsChanged")
                println("onPermissionsChanged:${args}")
            }
        }
        val proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), arrayOf(clazz), invokerHandler)

        val addOnPermissionsChangeListenerMethod = permissionService.javaClass.getMethod("addOnPermissionsChangeListener", clazz)
        addOnPermissionsChangeListenerMethod.invoke(permissionService, proxy)
        println("注册成功")
    }

}