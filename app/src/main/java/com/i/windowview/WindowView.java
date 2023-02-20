package com.i.windowview;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.view.ContextThemeWrapper;

import java.util.Objects;

public class WindowView extends ContextThemeWrapper {
    protected WindowManager mWindowManager;

    public WindowView(Context context, int theme) {
        super(context, theme);
    }
}
