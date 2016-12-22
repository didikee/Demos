package com.didikee.demos.ui.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.didikee.demos.R;
import com.didikee.demos.dao.sanjiao.SanJiaoDrawable;

public class SanJiaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_jiao);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        imageView.setImageDrawable(new SanJiaoDrawable());
    }
}
