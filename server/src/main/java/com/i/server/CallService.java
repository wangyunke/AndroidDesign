package com.i.server;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.i.aidl.ICall;

public class CallService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Dialog dialog = new Dialog(this);
        dialog.show();

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        Window window = dialog.getWindow();
//        window.setContentView();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        DeathRecipient recipient = new DeathRecipient(intent.getStringExtra("packageName"));
        try {
            iBinder.linkToDeath(recipient, 0);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return iBinder;
    }

    private final IBinder iBinder = new ICall.Stub(){

        @Override
        public void callBack(int a) {
            int pid = getCallingPid(); //client process id
            int uid = getCallingUid(); // client user id
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
}
