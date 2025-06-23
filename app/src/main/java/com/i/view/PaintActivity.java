package com.i.view;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import com.i.designpattern.R;
import com.i.designpattern.activity.BaseActivity;
import com.i.designpattern.databinding.ActivityPaintBinding;

public class PaintActivity extends BaseActivity {
    private ActivityPaintBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onClick();
    }

    private void onClick() {
        TrackView trackView = findViewById(R.id.tv);
        binding.clean.setOnClickListener(v -> trackView.clean());

        Spanned richText = Html.
                fromHtml("1、水电费加快速度见附件<br/>2、圣诞节疯狂拒收到付<br/>3、收到金陵饭店",
                Html.FROM_HTML_MODE_LEGACY);
        binding.rich.setText(richText);
    }
}
