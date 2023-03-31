package com.i.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TrackView extends View {
    Path mPath=new Path();

    private float mPreX,mPreY;

    public TrackView(Context context) {
        super(context);
    }

    public TrackView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TrackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TrackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                //直接依据点划线
                /*mPath.lineTo(300, 300);
                mPath.lineTo(400, 100);
                mPath.lineTo(500, 600);
                mPath.lineTo(600, 200);
                mPath.lineTo(700, 800);*/

                //贝塞尔曲线
                /*float endX = (mPreX + event.getX())/2;
                float endY = (mPreY + event.getY())/2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX=event.getX();
                mPreY=event.getY();*/
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawPath(mPath, paint);
    }

    public void clean(){
        mPath.reset();
        invalidate();
    }
}
