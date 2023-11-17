package com.i.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

/**
 * 反射系统@SystemApi的方法，apk需要是系统签名
 */
public class PermissionUtils {
    private final Context context;

    private final Object mPermissionManager;
    private Object mOnPermissionsChangeListener;
    private final List<String> mPermissions = Arrays.asList(Manifest.permission.CAMERA);
    /**
     * 只支持只有一个listener的情况
     */
    private InvocationHandler invocationHandler = (proxy, method, args) -> {
        if ("hashCode".equals(method.getName())) {
            return 0;
        } else if ("onPermissionsChanged".equals(method.getName())) {
            checkPermissions();
            return null;
        } else if ("equals".equals(method.getName())) {
            return true;
        }
        return null;
    };

    public PermissionUtils(Context context) {
        this.context = context;
        mPermissionManager = context.getSystemService("permission");
    }

    public void removeOnPermissionsChangeListener() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assert mPermissionManager != null;
        assert mOnPermissionsChangeListener != null;
        Class<?> clazz = getOnPermissionsChangeListenerClass();
        Method removeMethod = mPermissionManager.getClass().getMethod("removeOnPermissionsChangeListener", clazz);
        removeMethod.invoke(mPermissionManager, mOnPermissionsChangeListener);
        mOnPermissionsChangeListener = null;
    }

    public void addOnPermissionsChangeListener() throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        assert mPermissionManager != null;
        //check permissions once
        checkPermissions();
        if (mOnPermissionsChangeListener == null) {
            createOnPermissionsChangeListener();
        }
        Class<?> clazz = getOnPermissionsChangeListenerClass();
        Method addOnPermissionsChangeListenerMethod = mPermissionManager.getClass().getMethod("addOnPermissionsChangeListener", clazz);
        addOnPermissionsChangeListenerMethod.invoke(mPermissionManager, mOnPermissionsChangeListener);
    }

    private void checkPermissions() {
        for (String permission : mPermissions) {
            int result = ContextCompat.checkSelfPermission(context, permission);
            Log.e("Permissions", "checkPermissions: " + permission + "->" + result);
        }
    }

    public static boolean checkPermissions(Context context, String[] permissions) {
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(context, permission);
            Log.e("Permissions", "checkPermissions: " + permission + "->" + result);
            if(result == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    private void createOnPermissionsChangeListener() throws ClassNotFoundException {
        Class<?> clazz = getOnPermissionsChangeListenerClass();
        mOnPermissionsChangeListener = Proxy.newProxyInstance(mPermissionManager.getClass().getClassLoader(), new Class[]{clazz}, invocationHandler);
    }

    private Class<?> getOnPermissionsChangeListenerClass() throws ClassNotFoundException {
        return Class.forName("android.content.pm.PackageManager$OnPermissionsChangedListener");
    }
}
