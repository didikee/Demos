package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class WMRecyclerView extends RecyclerView{

    private boolean isWmShow=false;
    private OnPopShow popShowListener;


    public WMRecyclerView(Context context) {
        super(context);
    }

    public WMRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WMRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setWmShow(boolean wmShow) {
        isWmShow = wmShow;
    }

    public boolean getWmSHow(){
        return isWmShow;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
//        Log.e("re","re: "+e.getAction());
        if (isWmShow){
//            Log.e("test","true: "+e.getAction());
            if (popShowListener!=null){
                popShowListener.giveYouMyMotion(e);
            }
            Log.e("re","re-no-no: "+e.getAction());
            return true;
        }else {
            Log.e("re","re: "+e.getAction());
            return super.onTouchEvent(e);
        }

//        switch (e.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                return super.onTouchEvent(e);
//            case MotionEvent.ACTION_MOVE:
//                if (isWmShow){
//                    if (popShowListener!=null){
//                        popShowListener.giveYouMyMotion(e);
//                    }
//                    Log.e("re","re-no-no: "+e.getAction());
//                    return true;
//                }else {
//                    Log.e("re","re: "+e.getAction());
//                    return super.onTouchEvent(e);
//                }
//            case MotionEvent.ACTION_CANCEL:
//                return super.onTouchEvent(e);
//            case MotionEvent.ACTION_UP:
//                return super.onTouchEvent(e);
//            default:
//                return super.onTouchEvent(e);
//        }
    }

    public interface OnPopShow{
       void giveYouMyMotion(MotionEvent e);
    }

    public void setOnPopShowListener(OnPopShow popShowListener){
        this.popShowListener=popShowListener;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (isWmShow){
            return false;
        }else {
            return super.fling(velocityX,velocityY);
        }
    }


}
