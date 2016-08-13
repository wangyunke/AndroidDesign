package com.i.designpattern.defineview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ykw on 2016/7/16.
 */
public class SelfView extends View{

    public SelfView(Context context) { //调用时机：new Selfview(context)
        super(context);
    }

    public SelfView(Context context, AttributeSet attrs) { //调用时机：布局xml配置
        super(context, attrs);
    }

    public SelfView(Context context, AttributeSet attrs, int defStyleAttr) {//调用时机：布局xml配置并设置属性
        super(context, attrs, defStyleAttr);
    }

    public SelfView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }
}
