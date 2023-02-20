package com.i.server;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
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
        return iBinder;
    }

    private IBinder iBinder = new ICall.Stub(){

        @Override
        public void callBack(int a) throws RemoteException {

        }
    };
}
