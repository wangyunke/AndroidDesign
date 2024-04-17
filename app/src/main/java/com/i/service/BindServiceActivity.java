package com.i.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.i.aidl.ICall;
import com.i.designpattern.R;
import com.i.designpattern.activity.BaseActivity;

public class BindServiceActivity extends BaseActivity {
    private ICall mAidlCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_view);
        onClick();
    }

    private void onClick() {
        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(this, WindowViewService.class);
            startService(intent);
        });

        findViewById(R.id.bind).setOnClickListener(v -> {
            bindSerService();
        });
        findViewById(R.id.callback).setOnClickListener(v -> {
            try {
                mAidlCall.callBack(10);
            } catch (RemoteException ignored) {
            }
        });
        findViewById(R.id.send).setOnClickListener(v -> {
            try {
                byte[] dataToSend = {1, 2, 3, 9};
                mAidlCall.send(dataToSend);
            } catch (RemoteException ignored) {
            }
        });
    }

    private void bindSerService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.i.server", "com.i.server.CallService"));
        intent.putExtra("packageName", "com.i.service");
        bindService(intent, mSerCon, Context.BIND_AUTO_CREATE);
    }

    private final ServiceConnection mSerCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("CallService", "onServiceConnected name= " + name.getClassName());
            try {
                service.linkToDeath(new DeathRecipient(null), 1);

                mAidlCall = ICall.Stub.asInterface(service);
            } catch (RemoteException ignored) {
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private static class DeathRecipient implements IBinder.DeathRecipient {
        private final String client;

        public DeathRecipient(String clientId) {
            client = clientId;
        }

        @Override
        public void binderDied() {
            Log.i("CallService", "death client = " + client);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
