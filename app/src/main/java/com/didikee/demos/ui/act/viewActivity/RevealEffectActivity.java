package com.didikee.demos.ui.act.viewActivity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.DecelerateInterpolator;

import com.didikee.demos.R;

public class RevealEffectActivity extends AppCompatActivity {
    private View view;
    private double radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_effect);
        view = findViewById(R.id.view);
        findViewById(R.id.bt_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAnimation(view).start();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Animator createAnimation(View v) {

        Animator animator;
        if (radio == 0L) {
            radio = Math.sqrt(Math.pow(view.getWidth(), 2) + Math.pow(view.getHeight(), 2));
        }
        if (isOn) {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    0,// 动画开始的中心点X
                    0,// 动画开始的中心点Y
                    (float) radio,// 动画开始半径
                    0);// 动画结束半径
        } else {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    0,// 动画开始的中心点X
                    0,// 动画开始的中心点Y
                    0,// 动画开始半径
                    (float) radio);// 动画结束半径
        }
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (!isOn) {
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isOn) {
                    view.setVisibility(View.INVISIBLE);
                }
                isOn = !isOn;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(500);
        return animator;
    }
    private boolean isOn = true;//记录view的状态,第一次进去是可见的,记为true,不可见记为false
}
