package com.i.designpattern.activity;

import android.os.Bundle;

import com.i.designpattern.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        process();
    }

    /*public void process() {
        ImageView mImageView = (ImageView) findViewById(R.id.imageview);
        Glide.with(this)
                .load("http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg")
                .into(mImageView);

        ((TextView) findViewById(R.id.tv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,KnowledgeActivity.class);
                startActivity(intent);
            }
        });
    }*/
}
