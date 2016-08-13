package com.i.designpattern.defineview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by ykw on 2016/8/13.
 */
public class SelfViewGroup extends ViewGroup {
    public SelfViewGroup(Context context) {
        super(context);
    }

    public SelfViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SelfViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
