package com.i.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.i.designpattern.R;
import com.i.designpattern.activity.BaseActivity;
import com.i.hook.Catcher;
import com.i.service.WindowViewService;

public class PaintActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);



        onClick();
    }

    private void onClick() {
        TrackView trackView = findViewById(R.id.tv);
        findViewById(R.id.clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackView.clean();
            }
        });
    }
}
