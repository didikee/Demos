package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class LongClickLayout extends FrameLayout {

    private float longClickX;
    private float longClickY;
    private OnLayoutLongClickListener layoutLongClickListener;

    public LongClickLayout(Context context) {
        this(context,null);
    }

    public LongClickLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LongClickLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (layoutLongClickListener!=null)layoutLongClickListener.onLongClick(v,longClickX,longClickY);
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        longClickX=event.getRawX();
        longClickY=event.getRawY();
        return super.onTouchEvent(event);
    }

    public interface OnLayoutLongClickListener{
        void onLongClick(View v,float x,float y);
    }

    public void setOnLayoutLongClickListener(OnLayoutLongClickListener layoutLongClickListener){
        this.layoutLongClickListener=layoutLongClickListener;
    }
}
