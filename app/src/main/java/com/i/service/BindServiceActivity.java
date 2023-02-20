package com.i.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.i.aidl.ICall;
import com.i.designpattern.R;
import com.i.designpattern.activity.BaseActivity;

public class BindServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater.from(this);

        bindSerService();
    }

    private void bindSerService(){
        Intent intent=new Intent("");
        bindService(intent, mSerCon, 0);
    }

    private final ServiceConnection mSerCon=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ICall call = ICall.Stub.asInterface(service);
            try {
                call.callBack(10);
            } catch (RemoteException ignored) {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
    }
}
