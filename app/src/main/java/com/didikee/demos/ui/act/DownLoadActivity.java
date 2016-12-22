package com.didikee.demos.ui.act;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.didikee.demos.R;
import com.didikee.demos.dao.download.CallBack;
import com.didikee.demos.dao.download.DownloadConfiguration;
import com.didikee.demos.dao.download.DownloadException;
import com.didikee.demos.dao.download.DownloadManager;
import com.didikee.demos.dao.download.DownloadRequest;

import java.io.File;

public class DownLoadActivity extends AppCompatActivity {

    private final String TAG="Download";
//    private final String url="http://oahzrw11n.bkt.clouddn.com//apk/20161010/AVDNStv.acfundanmaku.video_320";
    private final String url="http://oahzrw11n.bkt.clouddn.com//apk/20161010/AV%E6%B5%8B%E8%AF%95tv.acfundanmaku.video_320.apk";
    private boolean isStart=false;
    private boolean isPause=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);

        final TextView tv_show = (TextView) findViewById(R.id.tv_show);
        Button bt = (Button) findViewById(R.id.bt_name);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStart){
                    if (isPause){
                        DownloadManager.getInstance().start(TAG);
                    }else {
                        DownloadManager.getInstance().pause(TAG);
                    }
                    isPause=!isPause;

                }else {
                    startDownload();
                    isStart=true;
                }
            }
        });

        initDownloadManager();
    }
    long length=0;
    private void startDownload() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File saveFile=new File(externalStorageDirectory,"mgxz_down");
        if (saveFile.exists()){
            //获取文件大小
            Log.e("test","----------");
            File tempFile=new File(saveFile,"AVDNStv.acfundanmaku.video_320"+".apk");
            length = tempFile.length();
        }else {
            saveFile.mkdir();
        }
        Log.e("test","save file: "+length);
        // first: build a DownloadRequest:
        final DownloadRequest request = new DownloadRequest.Builder()
                .setName("AVDNStv.acfundanmaku.video_320"+".apk")
                .setUri(url)
                .setFolder(saveFile)
                .build();

        // download:
        // the tag here, you can simply use download uri as your tag;
        DownloadManager.getInstance().download(request, TAG, new CallBack() {
            @Override
            public void onStarted() {
                Log.d(TAG,"onStarted");
            }

            @Override
            public void onConnecting() {
                Log.d(TAG,"onConnecting");
            }

            @Override
            public void onConnected(long total, boolean isRangeSupport) {
                Log.e(TAG,"onConnected");
                Log.e("test","save file: "+total);
                if (length==total){
                    DownloadManager.getInstance().cancelAll();
                }
            }

            @Override
            public void onProgress(long finished, long total, int progress) {
                Log.d(TAG,"onProgress"+" finished:"+finished +"  total:"+total+"  progress:"+progress);
            }

            @Override
            public void onCompleted() {
                Log.d(TAG,"onCompleted");
            }

            @Override
            public void onDownloadPaused() {
                Log.d(TAG,"onDownloadPaused");
            }

            @Override
            public void onDownloadCanceled() {
                Log.d(TAG,"onDownloadCanceled");
            }

            @Override
            public void onFailed(DownloadException e) {
                Log.d(TAG,"onFailed"+e.toString());
            }
        });
    }

    private void initDownloadManager() {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
    }
//
//    //pause
//    DownloadManager.getInstance().pause(tag);
//
////pause all
//    DownloadManager.getInstance().pauseAll();
//
////cancel
//    DownloadManager.getInstance().cancel(tag);
//
////cancel all
//    DownloadManager.getInstance().cancelAll();
//
////delete
//    DownloadManager.getInstance().delete(tag);
}
