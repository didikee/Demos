package com.didikee.demos.dao.huaban;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.didikee.uilibs.utils.DisplayUtil;

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

        int size = DisplayUtil.dp2px(activity, 100);
        huaBanLayout = new HuaBanLayout(activity);
        TextView textView=new TextView(activity);
        textView.setText("ceshi");
        textView.setTextSize(24);
        huaBanLayout.addView(textView,size,size);
        popupWindow = new PopupWindow(huaBanLayout, ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT,true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //设置背景
//        popupWindow.setAnimationStyle(R.style.pop_translate);
//        ColorDrawable background=new ColorDrawable(0x4F000000);
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
