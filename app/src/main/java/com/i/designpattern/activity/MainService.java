package com.i.designpattern.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MainService extends Service {

    @Override
    public void onCreate() {
        System.out.println("onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind"+intent.getStringExtra("intent"));
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand"+intent.getStringExtra("intent"));
        return super.onStartCommand(intent, flags, startId);
    }

}
