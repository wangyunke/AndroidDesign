package com.i.designpattern.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.i.designpattern.R;

public class KnowledgeActivity extends AppCompatActivity {
    private MyHandler mHandler=new MyHandler();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studyKnow();
    }

    private void studyKnow() {
        final TextView textView = (TextView)findViewById(R.id.tv);
        mHandler.sendMessageDelayed(Message.obtain(),50000);
    }

    public class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            System.out.println("执行了");
        }
    }

    @Override
    protected void onDestroy() {
        System.out.println("onDestory");
        super.onDestroy();
    }
}
