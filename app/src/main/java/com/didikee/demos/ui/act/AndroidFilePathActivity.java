package com.didikee.demos.ui.act;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.didikee.demos.R;

import java.io.File;

public class AndroidFilePathActivity extends AppCompatActivity {

    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_file_path);


        findViewById(R.id.bt_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllFilePath();
            }
        });

        tv_show = ((TextView) findViewById(R.id.tv_show));


    }

    private void getAllFilePath() {

        StringBuffer sb=new StringBuffer();
        File dataDirectory = Environment.getDataDirectory();
        sb.append("Environment.getDataDirectory(): "+"\n"+dataDirectory.getAbsolutePath()+"\n");

        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        sb.append("Environment.getDownloadCacheDirectory(): "+"\n"+downloadCacheDirectory.getAbsolutePath()+"\n");

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        sb.append("Environment.getExternalStorageDirectory(): "+"\n"+externalStorageDirectory.getAbsolutePath()+"\n");

//        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory();
        String externalStorageState = Environment.getExternalStorageState();
        sb.append("Environment.getExternalStorageState(): "+"\n"+externalStorageState+"\n");

        File rootDirectory = Environment.getRootDirectory();
        sb.append("Environment.getRootDirectory(): "+"\n"+rootDirectory.getAbsolutePath()+"\n");

        tv_show.setText(sb.toString());
    }
}
