package com.i.designpattern.activity.taskStack;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.view.KeyEventDispatcher;

import com.i.designpattern.R;
import com.i.designpattern.activity.BaseActivity;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


    }

    public void startC(View view){
        Intent intent = new Intent();
        ComponentName componentName=new ComponentName("cn.feng.skin.demo", "cn.feng.skin.demo.activity.Task2CActivity");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(componentName);
        startActivity(intent);
    }

}
