package com.i.designpattern.activity;

import android.content.Intent;
import android.os.Bundle;

import com.i.designpattern.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        process();
    }

    public void process() {
        Intent intent1=new Intent(this,MainIntentService.class);
        intent1.putExtra("intent","intent1");
        startService(intent1);

        Intent intent2=new Intent(this,MainIntentService.class);
        intent1.putExtra("intent","intent2");
        startService(intent2);

        Intent intent3=new Intent(this,MainIntentService.class);
        intent1.putExtra("intent","intent3");
        startService(intent3);
    }

}
