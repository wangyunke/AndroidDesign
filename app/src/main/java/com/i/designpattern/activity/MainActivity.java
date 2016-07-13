package com.i.designpattern.activity;

import android.os.AsyncTask;
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
        MyTask myTask=new MyTask();
        myTask.execute();
        myTask.cancel(true);
    }

    private class MyTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    }

}
