package com.i.hook;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class HookView extends View {
    public HookView(Context context) {
        super(context);
    }

    public HookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            Catcher.hookClick(getContext(), this);
        }
        return super.onTouchEvent(event);
    }

}
