package com.i.designpattern.activity;

import android.os.Bundle;

import com.i.designpattern.R;
import com.i.designpattern.templet.UseCarInstance;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        process();
    }

    public void process() {
        UseCarInstance.useCar();
    }
}
