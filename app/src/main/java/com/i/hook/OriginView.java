package com.i.hook;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public class OriginView extends HookView {
    public OriginView(Context context) {
        super(context);
    }

    public OriginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OriginView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
