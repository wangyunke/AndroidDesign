package com.i.designpattern.activity;

import android.os.Bundle;

import com.i.designpattern.R;
import com.i.designpattern.pubsub.PubSubInstance;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        process();
    }

    public void process() {
//        UseCarInstance.useCar(); //模板方法
//        FileKillInstance.kill(); //组合模式
//        CaseInstance.use(); //观察者模式
        PubSubInstance.use(); //发布订阅模式

    }
}
