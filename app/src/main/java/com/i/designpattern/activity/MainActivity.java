package com.i.designpattern.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.i.designpattern.R;
import com.i.hook.Catcher;
import com.i.service.WindowViewService;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater.from(this);

        onClick();
    }

    private void onClick() {
        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Catcher.TAG, "TextView/Button/ImageView等原生view被点击了");
                startDetail(null);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        LayoutInflater.Factory factory1=getLayoutInflater().getFactory();
        LayoutInflater.Factory factory2=getLayoutInflater().getFactory2();

//        Log.i("wangyunke", factory1==null);
//        Log.i("wangyunke", factory2.toString());
    }

    public void startDetail(View view){
        Intent intent = new Intent(this, WindowViewService.class);
        startService(intent);

//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.didichuxing.diia.carcenter","com.didichuxing.diia.carcenter.battery.BatteryChargeService"));
//        startService(intent);
    }

    public void process() {
//        UseCarInstance.useCar(); //模板方法
//        FileKillInstance.kill(); //组合模式
//        CaseInstance.use(); //观察者模式
//        PubSubInstance.use(); //发布订阅模式
    }
}
