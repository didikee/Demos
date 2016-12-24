package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.didikee.demos.R;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanLayout extends FrameLayout{

    private int startX=-1;
    private int startY=-1;

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

    public void initActMenu(){
        ImageView iv1=new ImageView(getContext());
        ImageView iv2=new ImageView(getContext());
        ImageView iv3=new ImageView(getContext());
    }

    @Override
    protected void onFinishInflate() {

    }

    public void setLocation(float x,float y){
        startX= (int) x;
        startY= (int) y;
        final View iv = findViewById(R.id.iv);
        iv.post(new Runnable() {
            @Override
            public void run() {
                FrameLayout.LayoutParams layoutParams = (LayoutParams) iv.getLayoutParams();
                int measuredWidth = iv.getWidth();
                int measuredHeight = iv.getHeight();
                Log.e("test","measuredWidth: "+measuredWidth +"measuredHeight: "+measuredHeight);
                layoutParams.setMargins(startX-measuredWidth/2,startY-measuredHeight/2,0,0);
                iv.setLayoutParams(layoutParams);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("test","huabanLayout: "+event.getRawX());
//        if (startX== -1 && startY ==-1){
//            startX= (int) event.getRawX();
//            startY= (int) event.getRawY();
//        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
//                Log.e("test","ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e("test","ACTION_MOVE");
                break;
            case MotionEvent.ACTION_OUTSIDE:
//                Log.e("test","ACTION_OUTSIDE");
                break;
            case MotionEvent.ACTION_UP:
//                Log.e("test","ACTION_UP");
                if (huaBanLayoutListener!=null){
                    huaBanLayoutListener.onFingerUp();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
//                Log.e("test","ACTION_CANCEL");
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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e("test","onLayout"+"X: "+startX +"Y: "+startY);
        super.onLayout(changed, left, top, right, bottom);
//        View iv = getChildAt(0);
//        int width = iv.getMeasuredWidth();
//        int height = iv.getMeasuredHeight();
//        Log.e("test","width: "+width+"height: "+height);
//        iv.layout(left+startX-width/2,top+startY-height/2,startX+width/2,startY+height/2);
//        iv.layout(100,100,width+100,height+100);
    }
}
