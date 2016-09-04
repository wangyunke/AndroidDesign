package com.i.designpattern.lock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.i.designpattern.R;

public class LockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        process();
    }

    private void process() {
        Entity1 entity1=new Entity1();
        Entity2 entity2=new Entity2();
//        System.out.println(entity1.iLock==entity2.iLock);

        entity1.start();
        entity2.start();

    }
}
