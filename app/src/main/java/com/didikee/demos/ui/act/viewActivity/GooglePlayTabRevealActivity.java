package com.didikee.demos.ui.act.viewActivity;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;

import com.didikee.demos.R;
import com.didikee.demos.ui.frag.PageFragment;
import com.didikee.demos.ui.tab.ExtTabLayout;
import com.didikee.demos.utils.ColorUtil;
import com.didikee.uilibs.utils.DisplayUtil;


public class GooglePlayTabRevealActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SimpleFragmentPagerAdapter pagerAdapter1;

    private ExtTabLayout tabLayout;
    private View viewAnimate;
    private View below;
    private float x;
    private float y;
    private int height;

    private int belowColor;
    private int systemStatusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_play_tab_reveal);

        setBarStyle();

        below = findViewById(R.id.below);
        viewAnimate = findViewById(R.id.act_view);

        systemStatusBarHeight = DisplayUtil.getSystemStatusBarHeight(this);

        pagerAdapter1 = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), new
                String[]{"tab1", "tab2", "tab3", "tab4"});
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (ExtTabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(ExtTabLayout.MODE_FIXED);

        viewPager.setAdapter(pagerAdapter1);

        belowColor = Color.parseColor(ColorUtil.random());
        below.setBackgroundColor(belowColor);
        viewAnimate.setBackgroundColor(belowColor);

        tabLayout.setLocationListener(new ExtTabLayout.LocationListener() {
            @Override
            public void location(float x, float y, int tabHeight) {
                GooglePlayTabRevealActivity.this.x = x;
                GooglePlayTabRevealActivity.this.y = y;
                GooglePlayTabRevealActivity.this.height = tabHeight;
            }
        });

        tabLayout.addOnTabSelectedListener(new ExtTabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTabSelected(ExtTabLayout.Tab tab) {
                Log.e("test", "x: " + x + "      y: " + y + "    height: " + height);
                final int width = viewAnimate.getWidth();
                final int height = viewAnimate.getHeight();
                final double radio = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
                float centerX = x;
                float centerY = y;
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(viewAnimate,
                        (int) centerX,
                        (int) centerY, 0, (float) radio);
                circularReveal.setInterpolator(new AccelerateInterpolator());
                circularReveal.setDuration(375);
                circularReveal.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        belowColor = Color.parseColor(ColorUtil.random());
                        viewAnimate.setBackgroundColor(belowColor);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        below.setBackgroundColor(belowColor);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                circularReveal.start();

            }

            @Override
            public void onTabUnselected(ExtTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(ExtTabLayout.Tab tab) {

            }
        });

    }

    public void setBarStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        private String tabTitles[];
        public SimpleFragmentPagerAdapter(FragmentManager fm, String[] strings) {
            super(fm);
            tabTitles = strings;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewAnimate.clearAnimation();
    }
}
