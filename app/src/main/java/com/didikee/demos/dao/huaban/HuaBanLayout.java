package com.didikee.demos.dao.huaban;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.didikee.demos.R;
import com.didikee.uilibs.utils.DisplayUtil;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanLayout extends FrameLayout {

    private final int totalRadio = 145;//0~~360,最好是0~~180
    private int radius;
    private int menuNum = 3;
    private final float scale = 1.5f;
    private final int duration=300;
    private final float endX=0.618f;//表示1/3

    private Pair<Rect, Rect> rectPair1;
    private Pair<Rect, Rect> rectPair2;
    private Pair<Rect, Rect> rectPair3;

    private int startX = -1;
    private int startY = -1;

    private HuaBanLayoutListener huaBanLayoutListener;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

    public HuaBanLayout(Context context) {
        this(context, null);
    }

    public HuaBanLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HuaBanLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_huaban, this, true);
        setFocusable(true);
        setBackgroundColor(Color.parseColor("#33000000"));
        initActMenu();

        initActParams();
    }

    private void initActParams() {
        radius = DisplayUtil.dp2px(getContext(), 90);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void initActMenu() {
        iv1 = new ImageView(getContext());
        iv2 = new ImageView(getContext());
        iv3 = new ImageView(getContext());

        iv1.setBackground(getResources().getDrawable(R.drawable.s_huaban_1));
        iv2.setBackground(getResources().getDrawable(R.drawable.s_huaban_1));
        iv3.setBackground(getResources().getDrawable(R.drawable.s_huaban_1));
        iv1.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), R.animator
                .s_huaban_1));
        iv2.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), R.animator
                .s_huaban_1));
        iv3.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), R.animator
                .s_huaban_1));

        int size = DisplayUtil.dp2px(getContext(), 48);
        iv1.setImageResource(R.drawable.ic_huaban_edit);
        iv2.setImageResource(R.drawable.ic_huaban_pin);
        iv3.setImageResource(R.drawable.ic_huaban_share);

        iv1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv3.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        addView(iv1, size, size);
        addView(iv2, size, size);
        addView(iv3, size, size);
    }

    @Override
    protected void onFinishInflate() {

    }

    public void startAnimate() {
        AnimatorSet tranSet1 = createAnimation(iv1, 1,true);
        iv1.setVisibility(VISIBLE);
        tranSet1.start();

        AnimatorSet tranSet2 = createAnimation(iv2, 2,true);
        iv2.setVisibility(VISIBLE);
        tranSet2.start();

        AnimatorSet tranSet3 = createAnimation(iv3, 3,true);
        iv3.setVisibility(VISIBLE);
        tranSet3.start();
    }

    public void endAnimate() {
        AnimatorSet tranSet1 = createAnimation(iv1, 1,false);
        tranSet1.start();

        AnimatorSet tranSet2 = createAnimation(iv2, 2,false);
        tranSet2.start();

        AnimatorSet tranSet3 = createAnimation(iv3, 3,false);
        tranSet3.start();
    }

    /**
     * 奇数的时候,中间的正好在中垂线上
     * @param index
     */
    private AnimatorSet createAnimation(View target, int index,boolean start) {
        float unitAngle = totalRadio * 1.0f / menuNum;

        if (index == 1) {
            float tranX = (float) (sin(unitAngle) * radius);
            float tranY = (float) (cos(unitAngle) * radius);
            Log.e("test", "tranX: " + tranX + "tranY: " + tranY);
            AnimatorSet tranSet = new AnimatorSet();

            if (start){
                ObjectAnimator translateX = ObjectAnimator.ofFloat(target, "translationX", 0, -tranX);
                ObjectAnimator translateY = ObjectAnimator.ofFloat(target, "translationY", 0, -tranY);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 0, 1);
                tranSet.playTogether(translateX, translateY,alpha);
                tranSet.setDuration(duration);
                tranSet.setInterpolator(new OvershootInterpolator());
            }else {
                ObjectAnimator translateX = ObjectAnimator.ofFloat(target, "translationX", -tranX,-tranX*endX);
                ObjectAnimator translateY = ObjectAnimator.ofFloat(target, "translationY", -tranY,-tranY*endX);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 1, 0);
                tranSet.playTogether(translateX, translateY,alpha);
                tranSet.setDuration((long) (duration));
                tranSet.setInterpolator(new DecelerateInterpolator());
            }

            Rect rect1 = new Rect((int) (startX - tranX - target.getWidth() / 2), (int) (startY -
                    tranY - target.getHeight() / 2), (int) (startX - tranX + target.getWidth() / 2),
                    (int) (startY - tranY + target.getHeight() / 2));
            Rect rect2 = new Rect((int) (startX - tranX - target.getWidth() * scale / 2), (int)
                    (startY - tranY - target.getHeight() * scale / 2), (int) (startX - tranX +
                    target.getWidth() * scale / 2),
                    (int) (startY - tranY + target.getHeight() * scale / 2));
            rectPair1 = new Pair<>(rect1, rect2);
            return tranSet;
        }

        if (index == 3) {
            float tranX = (float) (sin(unitAngle) * radius);
            float tranY = (float) (cos(unitAngle) * radius);
            Log.e("test", "tranX: " + tranX + "tranY: " + tranY);


            AnimatorSet tranSet = new AnimatorSet();

            if (start){
                ObjectAnimator translateX = ObjectAnimator.ofFloat(target, "translationX", 0, tranX);
                ObjectAnimator translateY = ObjectAnimator.ofFloat(target, "translationY", 0, -tranY);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 0, 1);
                tranSet.playTogether(translateX, translateY,alpha);
                tranSet.setDuration(duration);
                tranSet.setInterpolator(new OvershootInterpolator());
            }else {
                ObjectAnimator translateX = ObjectAnimator.ofFloat(target, "translationX", tranX,tranX*endX);
                ObjectAnimator translateY = ObjectAnimator.ofFloat(target, "translationY", -tranY,-tranY*endX);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 1, 0);
                tranSet.playTogether(translateX, translateY,alpha);
                tranSet.setDuration((long) (duration));
                tranSet.setInterpolator(new DecelerateInterpolator());
            }

            Rect rect1 = new Rect((int) (startX + tranX - target.getWidth() / 2), (int) (startY -
                    tranY - target.getHeight() / 2), (int) (startX + tranX + target.getWidth() / 2),
                    (int) (startY - tranY + target.getHeight() / 2));
            Rect rect2 = new Rect((int) (startX + tranX - target.getWidth() * scale / 2), (int)
                    (startY - tranY - target.getHeight() * scale / 2), (int) (startX + tranX +
                    target.getWidth() * scale / 2),
                    (int) (startY - tranY + target.getHeight() * scale / 2));
            rectPair3 = new Pair<>(rect1, rect2);
            return tranSet;
        }
        if (index == 2) {
            int tranY = radius;
            AnimatorSet tranSet = new AnimatorSet();

            if (start){
                ObjectAnimator translateY = ObjectAnimator.ofFloat(target, "translationY", 0, -tranY);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 0, 1);
                tranSet.playTogether( translateY,alpha);
                tranSet.setDuration(duration);
                tranSet.setInterpolator(new OvershootInterpolator());
            }else {
                ObjectAnimator translateY = ObjectAnimator.ofFloat(target, "translationY", -tranY,-tranY*endX);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(target, "alpha", 1, 0);
                tranSet.playTogether( translateY,alpha);
                tranSet.setDuration((long) (duration));
                tranSet.setInterpolator(new DecelerateInterpolator());
            }


            Rect rect1 = new Rect((int) (startX  - target.getWidth() / 2), (int) (startY -
                    tranY - target.getHeight() / 2), (int) (startX  + target.getWidth() / 2),
                    (int) (startY - tranY + target.getHeight() / 2));
            Rect rect2 = new Rect((int) (startX - target.getWidth() * scale / 2), (int)
                    (startY - tranY - target.getHeight() * scale / 2), (int) (startX  +
                    target.getWidth() * scale / 2),
                    (int) (startY - tranY + target.getHeight() * scale / 2));
            rectPair2 = new Pair<>(rect1, rect2);
            return tranSet;
        }
        return null;
    }

    private double sin(float angle) {
        return Math.sin(Math.toRadians(angle));
    }

    private double cos(float angle) {
        return Math.cos(Math.toRadians(angle));
    }

    private boolean isInRect(float x, float y, Rect rect) {
        return (rect != null && (x >= rect.left && x <= rect.right) && (y >= rect.top && y <=
                rect.bottom));
    }

    public void setLocation(float x, float y) {
        startX = (int) x;
        startY = (int) y;
        final View iv = findViewById(R.id.iv);
        iv.post(new Runnable() {
            @Override
            public void run() {
                FrameLayout.LayoutParams layoutParams = (LayoutParams) iv.getLayoutParams();
                int measuredWidth = iv.getWidth();
                int measuredHeight = iv.getHeight();
                Log.e("test", "measuredWidth: " + measuredWidth + "measuredHeight: " +
                        measuredHeight);
                layoutParams.setMargins(startX - measuredWidth / 2, startY - measuredHeight / 2,
                        0, 0);
                iv.setLayoutParams(layoutParams);

                iv1.setLayoutParams(layoutParams);
                iv2.setLayoutParams(layoutParams);
                iv3.setLayoutParams(layoutParams);

                iv1.setVisibility(INVISIBLE);
                iv2.setVisibility(INVISIBLE);
                iv3.setVisibility(INVISIBLE);

                startAnimate();
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
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        Log.e("test", "rawX: " + rawX + "rawY: " + rawY);
        if (rectPair1 != null) {
            if (iv1.isActivated()) {
                if (isInRect(rawX, rawY, rectPair1.second)) {
                    if (!iv1.isActivated()) {
                        iv1.setActivated(true);
                    }

                } else {
                    if (iv1.isActivated()) {
                        iv1.setActivated(false);
                    }
                }
            } else {
                if (isInRect(rawX, rawY, rectPair1.first)) {
                    if (!iv1.isActivated()) {
                        iv1.setActivated(true);
                    }

                } else {
                    if (iv1.isActivated()) {
                        iv1.setActivated(false);
                    }
                }
            }

            if (iv2.isActivated()) {
                if (isInRect(rawX, rawY, rectPair2.second)) {
                    if (!iv2.isActivated()) {
                        iv2.setActivated(true);
                    }

                } else {
                    if (iv2.isActivated()) {
                        iv2.setActivated(false);
                    }
                }
            } else {
                if (isInRect(rawX, rawY, rectPair2.first)) {
                    if (!iv2.isActivated()) {
                        iv2.setActivated(true);
                    }

                } else {
                    if (iv2.isActivated()) {
                        iv2.setActivated(false);
                    }
                }
            }

            if (iv3.isActivated()) {
                if (isInRect(rawX, rawY, rectPair3.second)) {
                    if (!iv3.isActivated()) {
                        iv3.setActivated(true);
                    }

                } else {
                    if (iv3.isActivated()) {
                        iv3.setActivated(false);
                    }
                }
            } else {
                if (isInRect(rawX, rawY, rectPair3.first)) {
                    if (!iv3.isActivated()) {
                        iv3.setActivated(true);
                    }

                } else {
                    if (iv3.isActivated()) {
                        iv3.setActivated(false);
                    }
                }
            }
        }


        switch (event.getAction()) {
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
                endAnimate();
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (huaBanLayoutListener != null) {
                            huaBanLayoutListener.onFingerUp();
                        }
                        if (iv1.isActivated()) {
                            Log.e("test", "选中 1 ..................");
                        }

                        if (iv2.isActivated()){
                            Log.e("test", "选中 2 ..................");
                        }

                        if (iv3.isActivated()){
                            Log.e("test", "选中 3 ..................");
                        }
                    }
                }, (long) (duration));

                break;
            case MotionEvent.ACTION_CANCEL:
//                Log.e("test","ACTION_CANCEL");
                break;
        }
        return true;
    }

    public interface HuaBanLayoutListener {
        void onFingerUp();
    }

    public void setHuaBanLayoutListener(HuaBanLayoutListener huaBanLayoutListener) {
        this.huaBanLayoutListener = huaBanLayoutListener;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.e("test","onLayout"+"X: "+startX +"Y: "+startY);
        super.onLayout(changed, left, top, right, bottom);
//        View iv = getChildAt(0);
//        int width = iv.getMeasuredWidth();
//        int height = iv.getMeasuredHeight();
//        Log.e("test","width: "+width+"height: "+height);
//        iv.layout(left+startX-width/2,top+startY-height/2,startX+width/2,startY+height/2);
//        iv.layout(100,100,width+100,height+100);
    }
}
