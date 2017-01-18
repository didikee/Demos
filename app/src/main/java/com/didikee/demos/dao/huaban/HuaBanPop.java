package com.didikee.demos.dao.huaban;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanPop {
    private Activity activity;
    private PopupWindow popupWindow;
    private PopListener popListener;
    private HuaBanLayout huaBanLayout;

    public HuaBanPop(Activity activity) {
        this.activity = activity;
        initPop();
    }


    private void initPop() {
        popupWindow = new PopupWindow(activity);
        huaBanLayout = new HuaBanLayout(activity);
        popupWindow = new PopupWindow(huaBanLayout, ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT,true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //设置背景
//        popupWindow.setAnimationStyle(R.style.pop_translate);
        ColorDrawable background=new ColorDrawable(Color.parseColor("#33000000"));
//        //设置背景+
        popupWindow.setBackgroundDrawable(background);
//
//        popupWindow.setFocusable(true);

        popupWindow.setOutsideTouchable(true);

        popupWindow.setClippingEnabled(false);

        huaBanLayout.setHuaBanLayoutListener(new HuaBanLayout.HuaBanLayoutListener() {
            @Override
            public void onFingerUp() {
                if (popListener!=null){
                    popListener.onDismiss();
                }
                popupWindow.dismiss();
            }
        });
    }

    public void setLocationForLayout(float x,float y){
        huaBanLayout.setLocation(x,y);
    }

    public void setOtherMotion(MotionEvent event){
        huaBanLayout.onTouchEvent(event);
    }

    public void show(){
        popupWindow.showAtLocation(activity.findViewById(android.R.id.content), Gravity.CENTER,0,0);
    }

    public interface PopListener{
        void onDismiss();
    }

    public void setPopListener(PopListener popListener){
        this.popListener=popListener;
    }
}
