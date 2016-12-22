package com.didikee.demos.ui.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.demos.R;
import com.didikee.demos.dao.FileChooser;

import java.io.File;

public class FileChooserActivity extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_chooser);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_path = (TextView) findViewById(R.id.tv_path);


        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });
    }
//intent.setType(“image/*”);
//intent.setType(“audio/*”); //选择音频
//intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
//intent.setType(“video/*;image/*”);//同时选择视频和图片

    private void open() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("test","requestCode: "+requestCode +" resultCode: "+resultCode);
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
//            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
//            String[] proj = {MediaStore.Images.Media.DATA};
//            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
//            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            actualimagecursor.moveToFirst();
//            String img_path = actualimagecursor.getString(actual_image_column_index);
//            File file = new File(img_path);
//            Toast.makeText(FileChooserActivity.this, file.toString(), Toast.LENGTH_SHORT).show();
            FileChooser fileChooser=new FileChooser();
            String filePath="";
            int sdkInt = Build.VERSION.SDK_INT;
            if (sdkInt >=19){
                filePath = fileChooser.getRealPathFromURI_API19
                        (FileChooserActivity.this, data.getData());
                tv_path.setText(filePath);
            }else if (sdkInt >=11 && sdkInt <=18){
                filePath = fileChooser.getRealPathFromURI_API11to18
                        (FileChooserActivity.this, data.getData());
                tv_path.setText(filePath);
            }else {
                filePath = fileChooser.getRealPathFromURI_BelowAPI11
                        (FileChooserActivity.this, data.getData());
                tv_path.setText(filePath);
            }
            if (TextUtils.isEmpty(filePath)){
                Toast.makeText(this, "Failed! ", Toast.LENGTH_SHORT).show();
                return;
            }
            File file=new File(filePath);
            long length = file.length();
            String name = file.getName();
            tv_name.setText(name);
            tv_path.setText("path:"+filePath+"长度: "+length);
        }
    }
}
