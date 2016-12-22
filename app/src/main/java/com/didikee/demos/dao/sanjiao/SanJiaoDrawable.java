package com.didikee.demos.dao.sanjiao;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by didik 
 * Created time 2016/12/8
 * Description: 
 */

public class SanJiaoDrawable extends Drawable {
    private Paint mPaint;
    public SanJiaoDrawable() {
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
    }

    @Override
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds==null){
            Log.e("test","hhhhh");
            return;
        }
        Path sanJiao=new Path();
        sanJiao.moveTo(bounds.right/2,0);
        sanJiao.lineTo(0,bounds.bottom);
        sanJiao.lineTo(bounds.right,bounds.bottom);
        sanJiao.close();
//        canvas.drawRect(bounds,mPaint);
        canvas.drawPath(sanJiao,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        Log.e("test",left+"--"+top+"--"+right+"--"+bottom);
    }
}
