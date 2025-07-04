package com.i.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Process

object Utils {

    fun isPermissionGranted(context: Context, permission: String): Boolean {
        val ret: Boolean
        if (Binder.getCallingPid() == Process.myPid()) {
            ret = context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        } else {
            ret = context.checkCallingPermission(permission) == PackageManager.PERMISSION_GRANTED
        }
        return ret
    }

}