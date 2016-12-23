package com.didikee.demos.dao.huaban;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.didikee.uilibs.utils.DisplayUtil;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanViewHelper {
    private final Activity activity;
    private FrameLayout rootContent;
    private HuaBanLayout huaBanLayout;

    public HuaBanViewHelper(Activity activity) {
        this.activity = activity;
        initView();
    }

    private void initView() {
        int size = DisplayUtil.dp2px(activity, 100);
        huaBanLayout = new HuaBanLayout(activity);
        TextView textView=new TextView(activity);
        textView.setText("ceshi");
        textView.setTextSize(24);
        huaBanLayout.addView(textView,size,size);
    }

    public void show(){
        rootContent = ((FrameLayout) activity.findViewById(android.R.id.content));
        rootContent.addView(huaBanLayout, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void dismiss(){
        rootContent.removeView(huaBanLayout);
    }
}
