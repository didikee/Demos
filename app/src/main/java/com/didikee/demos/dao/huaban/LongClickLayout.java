package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class LongClickLayout extends FrameLayout {
    public LongClickLayout(Context context) {
        super(context);
    }

    public LongClickLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LongClickLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        super.setOnLongClickListener(l);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }
}
