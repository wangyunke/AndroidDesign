package com.i.designpattern.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.i.designpattern.R;
import com.i.hook.Catcher;
import com.i.service.WindowViewService;
import com.i.utils.LogUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aem_wake_detect);
        LayoutInflater.from(this);

//        onClick();
        setTip();
        listenScreen();
    }

    private void listenScreen() {
        LogUtil.i("ScreenOnOffReceiver", "ScreenStatusReceiver register");
        ScreenStatusReceiver screenReceiver = new ScreenStatusReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(screenReceiver, filter);
    }

    private void setTip() {
        String pcm =
                "<b><font>如果以上全都无问题，请进行如下操作</font></b>" +
                        "<br><font>抓PCM音频数据，步骤如下：</font></br>" +
                        "<br><font color=\"blue\">1、打开dump开关：1:保存音频  0:不保存音频</font><br/>" +
                        "<font color=\"red\"></font>adb shell am broadcast -a com.aispeech.lyra.action.debug -f 0x01000000 --ei \"val\" 1</font><br/>" +
                        "<b><font color=\"blue\"></font>2、执行语音唤醒操作，自动生成audio pcm数据</font></b><br/>" +
                        "b><font color=\"blue\"></font>3、查询userId, 替换4下面路径里面的userId</font>b><br/>" +
                        "<font color=\"red\"></font>adb shell ps -ef |grep assistant</font><br/>" +
                        "<b><font color=\"blue\"></font>4、导出audio pcm数据</font></b><br/>" +
                        "<font color=\"blue\"></font>/data/user/(userId)/technology.cariad.assistant/files/origin_**.pcm</font><br/>" +
                        "<font color=\"blue\"></font>/data/user/(userId)/technology.cariad.assistant/files/resample_**.pcm</font><br/>" +
                        "<b><font color=\"blue\"></font>5、录完音频记得关掉dump开关</font></b><br/>" +
                        "<font color=\"red\"></font>adb shell am broadcast -a com.aispeech.lyra.action.debug -f 0x01000000 --ei \"val\" 0</font><br/>";
//        ((TextView)findViewById(R.id.tip_pcm)).setText(Html.fromHtml(pcm, Html.FROM_HTML_MODE_LEGACY));
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
