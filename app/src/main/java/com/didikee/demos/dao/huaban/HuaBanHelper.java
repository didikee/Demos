package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.didikee.uilibs.utils.DisplayUtil;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanHelper {
    private final Context context;
    private WindowManager windowManager;
    private WindowManager.LayoutParams wmParams;


    public HuaBanHelper(Context context) {
        this.context = context;
        initWindowManager();
    }

    private void initWindowManager() {
       windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wmParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        } else {
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        wmParams.flags=WindowManager.LayoutParams.FLAG_FULLSCREEN|WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        wmParams.gravity= Gravity.START|Gravity.TOP;
        wmParams.format= PixelFormat.RGBA_8888;

        wmParams.width=WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height=WindowManager.LayoutParams.MATCH_PARENT;

    }

    public void show(){
        int size = DisplayUtil.dp2px(context, 100);
        HuaBanLayout huaBanLayout=new HuaBanLayout(context);
        TextView textView=new TextView(context);
        textView.setText("ceshi");
        textView.setTextSize(24);
        huaBanLayout.addView(textView,size,size);
        windowManager.addView(huaBanLayout,wmParams);
    }

    public void dismiss(){

    }


}
