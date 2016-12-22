package com.didikee.demos.dao.miguoMain;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by didik 
 * Created time 2016/12/21
 * Description: 
 */

public class HFlingRecyclerView extends HRecyclerView{

    public HFlingRecyclerView(Context context) {
        super(context);
    }

    public HFlingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HFlingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
}
