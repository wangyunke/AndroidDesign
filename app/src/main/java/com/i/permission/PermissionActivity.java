package com.i.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.i.designpattern.R;
import com.i.designpattern.activity.BaseActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class PermissionActivity extends BaseActivity {
    private static final String TAG = "PermissionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);


        onClick();
        startMonitoring();
    }


    private void onClick() {
        findViewById(R.id.requestPermission).setOnClickListener(v -> {

            int permission = ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CAMERA);
            if (permission == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "-----permission=true---");
            } else {
                //没有权限，提示获取权限
                String[] perms = {Manifest.permission.READ_CALL_LOG, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA};
                ActivityCompat.requestPermissions(PermissionActivity.this, perms, 1);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch(permsRequestCode){
            case 1:
                boolean accepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                Log.i(TAG, "-----permissions="+ Arrays.stream(permissions).toString()+", grantResults=--"+accepted);
                break;
        }
    }

    private Method addOnPermissionsChangeListener = null;
    private Method removeOnPermissionsChangeListener = null;
    private Object listenerInstance = null;

    public boolean startMonitoring() {
        try {
            PackageManager packageManager = this.getPackageManager();
            if (this.addOnPermissionsChangeListener == null || this.removeOnPermissionsChangeListener == null || this.listenerInstance == null) {
                Optional findFirst = Arrays.stream(packageManager.getClass().getClasses()).filter(new Predicate() {
                    @Override
                    public final boolean test(Object obj) {
                        Class clz = (Class) obj;
                        Log.i(TAG, "-----clz.getName()-----" + clz.getName());
                        return "android.content.pm.PackageManager$OnPermissionsChangedListener".equals(clz.getName());
                    }
                }).findFirst();
                Class<?>[] clsArr = {(Class) findFirst.get()};
                Log.i(TAG, "-----clsArr.getName()-----" + clsArr);
                this.addOnPermissionsChangeListener = packageManager.getClass().getDeclaredMethod("addOnPermissionsChangeListener", clsArr);
                this.removeOnPermissionsChangeListener = packageManager.getClass().getDeclaredMethod("removeOnPermissionsChangeListener", clsArr);
                this.listenerInstance = Proxy.newProxyInstance(clsArr[0].getClassLoader(), clsArr, new InvocationHandler() {
                    @Override
                    public Object invoke(Object obj, Method method, Object[] objArr) throws Exception {
                        return handleInvoke(method);
                    }
                });
            }
            this.addOnPermissionsChangeListener.invoke(packageManager, this.listenerInstance);
        } catch (Exception e) {
        }
        return true;
    }

    public Object handleInvoke(Method method) throws Exception {
        String name = method.getName();
        Log.i(TAG, "----handleInvoke------"+name);
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (name.equals("onPermissionsChanged") && parameterTypes.length == 1 && parameterTypes[0] == Integer.TYPE) {
            Log.i(TAG, "----parameterTypes------"+parameterTypes);
            return null;
        } else if (Integer.TYPE == method.getReturnType()) {
            return 0;
        } else {
            if (Boolean.TYPE == method.getReturnType()) {
                return false;
            }
            return method.getReturnType().newInstance();
        }
    }

    private View getInstance(String className) {
        Object object = null;
        try {
            Class clazz = Class.forName(className);
            Constructor con = clazz.getDeclaredConstructor(Context.class);
            object = con.newInstance(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (View) object;
    }

}
