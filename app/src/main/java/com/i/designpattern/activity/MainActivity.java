package com.i.designpattern.activity;

import android.os.Bundle;

import com.i.designpattern.R;

import java.util.concurrent.Executors;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        process();
    }

    public void process() {
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(10);
    }

}
