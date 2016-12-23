package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanLayout extends FrameLayout{

    private HuaBanLayoutListener huaBanLayoutListener;

    public HuaBanLayout(Context context) {
        this(context,null);
    }

    public HuaBanLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HuaBanLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        LayoutInflater.from(context).inflate(R.layout.layout_huaban,this,true);
        setFocusable(true);
        setBackgroundColor(Color.parseColor("#33000000"));
    }

    @Override
    protected void onFinishInflate() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("test","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("test","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.e("test","ACTION_OUTSIDE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("test","ACTION_UP");
                if (huaBanLayoutListener!=null){
                    huaBanLayoutListener.onFingerUp();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("test","ACTION_CANCEL");
                break;
        }
//        Log.e("test",""+event.getAction());
        return true;
    }

    public interface HuaBanLayoutListener{
        void onFingerUp();
    }

    public void setHuaBanLayoutListener(HuaBanLayoutListener huaBanLayoutListener){
        this.huaBanLayoutListener=huaBanLayoutListener;
    }
}
