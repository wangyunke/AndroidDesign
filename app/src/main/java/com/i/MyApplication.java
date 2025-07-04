package com.i;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentHostCallback;
import androidx.fragment.app.FragmentTransaction;

import com.i.anr.LooperPrinter;

public class MyApplication extends Application {
    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        Looper.getMainLooper().setMessageLogging(new LooperPrinter());
    }

    void createFragment(Fragment fragment, int viewId){
        HostCallBack callBack = new HostCallBack(this, new Handler(), 0);
        FragmentController controller = FragmentController.createController(callBack);
        FragmentTransaction transaction = controller.getSupportFragmentManager().beginTransaction();
        transaction.add(viewId, fragment);
        transaction.commit();
    }

    static class HostCallBack extends FragmentHostCallback {
        public HostCallBack(@NonNull Context context, @NonNull Handler handler, int windowAnimations) {
            super(context, handler, windowAnimations);
        }

        @Nullable
        @Override
        public Object onGetHost() {
            return null;
        }
    }
}
