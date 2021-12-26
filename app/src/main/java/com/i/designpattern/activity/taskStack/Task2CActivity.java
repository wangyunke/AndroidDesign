package com.i.designpattern.activity.taskStack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.i.designpattern.R;
import com.i.designpattern.activity.BaseActivity;

/**
 * 配置android:taskAffinity="com.i.newTask"，（启动模式 FLAG_ACTIVITY_NEW_TASK 无配置）
 * 则不会开启新的任务栈
 *
 *      * Task{1c5460 #18 type=standard A=10148:com.i.newTask U=0 visible=true mode=fullscreen translucent=false sz=1} bounds=[0,0][720,1280]
 *         * ActivityRecord{5504ec7 u0 com.i.designpattern/.activity.taskStack.Task2DActivity t18}
 *
 *       * Task{cf33c5e #17 type=standard A=10148:com.i.designpattern U=0 visible=false mode=fullscreen translucent=true sz=3} bounds=[0,0][720,1280]
 *         * ActivityRecord{3d8b1e8 u0 com.i.designpattern/.activity.taskStack.Task2CActivity t17}
 *         * ActivityRecord{61cc307 u0 com.i.designpattern/.activity.taskStack.DetailActivity t17}
 *         * ActivityRecord{74d8761 u0 com.i.designpattern/.activity.MainActivity t17}
 */

public class Task2CActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ((TextView)findViewById(R.id.tv)).setText("Task2 C activity ");
    }

    public void startC(View view){
        Intent intent = new Intent(this, Task2DActivity.class);
        startActivity(intent);
    }

}
