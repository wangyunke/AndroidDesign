package com.i.hook;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

public class Catcher {
    public static final String TAG = "Catcher";

    public static void hookClick(Context context, View view) {
        Log.i(TAG, "context is Activity=" + (context instanceof Activity));
        if (context instanceof Activity) {
            Log.i(TAG, "packageName=" + ((Activity) context).getPackageName());
            Log.i(TAG, "Activity=" + ((Activity) context).getClass().getName());
        }

        Log.i(TAG, "viewId=" + view.getId());

        ViewParent pare = view.getParent();
        StringBuilder sb = new StringBuilder();
        sb.append(view.getClass()).append("/").append(pare.getClass());
        while (true) {
            pare = pare.getParent();
            if (pare == null) {
                break;
            }
            sb.append("/" + pare.getClass());

        }
        Log.i(TAG, "viewPath=" + sb);
    }
}
