package com.didikee.demos.dao;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;

/**
 * Created by didik on 2016/11/29.
 */

public class YaoYiYaoUtil {
    private SensorManager sensorManager;

    private Activity activity;
    private SensorEventListener sensorEventListener;


    public YaoYiYaoUtil(Activity activity,SensorEventListener listener) {
        if (activity==null)return;
        this.activity=activity;
        this.sensorEventListener=listener;
        Log.e("Test","型号: "+ Build.MODEL);
        Log.e("Test","品牌: "+ Build.BRAND);
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
    }

    public void registerShakeListener(){
        if (sensorManager!=null){
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void unregisterShakeListener(){
        if (sensorManager!=null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
}
