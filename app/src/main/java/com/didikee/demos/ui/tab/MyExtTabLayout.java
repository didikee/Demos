package com.didikee.demos.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by didik 
 * Created time 2016/12/22
 * Description: 
 */

public class MyExtTabLayout extends ExtTabLayout {
    public MyExtTabLayout(Context context) {
        super(context);
    }

    public MyExtTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyExtTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

//    protected class TouchTabView extends ExtTabLayout.TabView{
//        private float x=0f;
//        private float y=0f;
//        public TouchTabView(Context context) {
//            super(context);
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            if (event.getAction()==MotionEvent.ACTION_UP){
//                x=event.getX();
//                y=event.getY();
//            }
//            return super.onTouchEvent(event);
//        }
//
//        @Override
//        public boolean performClick() {
//            boolean b = super.performClick();
//            if (b && locationListener!=null){
//                locationListener.location(x,y);
//            }
//            return b;
//        }
//    }

//    public interface LocationListener{
//        void location(float x,float y);
//    }
//
//    private LocationListener locationListener;
//
//    public void setLocationListener(LocationListener locationListener) {
//        this.locationListener = locationListener;
//    }
}
