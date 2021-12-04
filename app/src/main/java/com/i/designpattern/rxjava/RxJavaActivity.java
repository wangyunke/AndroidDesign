package com.i.designpattern.rxjava;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.i.designpattern.R;

public class RxJavaActivity extends AppCompatActivity implements UserView {
    private TextView mNameTV;
    private TextView mAgeTV;
    private ImageView mImageView;
    private ProgressDialog mProgressDialog;
    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        mUserPresenter = new UserPresenter(this);
        initView();
    }

    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在加载，请稍后..");

        mNameTV=(TextView) findViewById(R.id.name);
        mAgeTV=(TextView) findViewById(R.id.age);
        mImageView=(ImageView) findViewById(R.id.image);

        TextView mActionAsynTV=(TextView) findViewById(R.id.action_asyn);
        TextView mActionNetTV=(TextView) findViewById(R.id.action_net);
        mActionAsynTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPresenter.getUserDb();
            }
        });
        mActionNetTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPresenter.getNetReq();
            }
        });
    }

    @Override
    public void updateUI(User user) {
        mNameTV.setText(user.getName());
        mAgeTV.setText(user.getAge());
    }

    @Override
    public void updateUI(Joke joke) {
        mNameTV.setText(joke.getAuthor());
        mAgeTV.setText(joke.getContent());
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }
}
