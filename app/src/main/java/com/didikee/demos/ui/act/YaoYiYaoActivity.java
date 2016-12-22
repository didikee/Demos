package com.didikee.demos.ui.act;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.didikee.demos.R;
import com.didikee.demos.dao.YaoYiYaoUtil;

public class YaoYiYaoActivity extends AppCompatActivity {

    private TextView tv_x;
    private TextView tv_y;
    private TextView tv_z;
    private YaoYiYaoUtil yaoUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yao_yi_yao);

        tv_x = ((TextView) findViewById(R.id.tv_x));
        tv_y = ((TextView) findViewById(R.id.tv_y));
        tv_z = ((TextView) findViewById(R.id.tv_z));

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        yaoUtil = new YaoYiYaoUtil(this,sensorEventListener);
        yaoUtil.registerShakeListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (yaoUtil!=null){
            yaoUtil.unregisterShakeListener();
        }
    }
    private Vibrator vibrator;
    private static final float SENSOR_SHAKE = 19;
    private long first=0;
    private float testMaxValueX=0f;
    private float testMaxValueY=0f;
    private float testMaxValueZ=0f;
    /**
     * 重力感应监听
     */
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            Log.v("Test", "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            float middleValue = SENSOR_SHAKE;
            float absX = Math.abs(x);
            float absY = Math.abs(y);
            float absZ = Math.abs(z);
            if (absX > testMaxValueX){
                testMaxValueX=absX;
                Log.e("Test","x轴 max: "+testMaxValueX);
                tv_x.setText("x轴 max: "+testMaxValueX);
            }
            if (absY > testMaxValueY){
                testMaxValueY=absY;
                Log.e("Test","y轴 max: "+testMaxValueY);
                tv_y.setText("y轴 max: "+testMaxValueY);
            }
            if (absZ > testMaxValueZ){
                testMaxValueZ=absZ;
                Log.e("Test","z轴 max: "+testMaxValueZ);
                tv_z.setText("z轴 max: "+testMaxValueZ);
            }
            if ( absX> middleValue || absY > middleValue || absZ > middleValue) {
                vibrator.vibrate(200);
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis-first>100){
                    first=currentTimeMillis;
//                    SDActivityUtil.startActivity(activity, DevActivity.class);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
