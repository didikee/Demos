package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by didik 
 * Created time 2016/12/26
 * Description: 
 */

public class TempRV extends RecyclerView {
    public TempRV(Context context) {
        super(context);
    }

    public TempRV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TempRV(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean b = super.onTouchEvent(e);
        Log.e("test","result: "+b +"MotionEvent: "+e.getAction());
        return b;
    }
}
