package com.didikee.demos.ui.act.viewActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.didikee.demos.R;
import com.didikee.demos.dao.huaban.HuaBanDialog;
import com.didikee.demos.dao.huaban.HuaBanDivier;
import com.didikee.demos.dao.huaban.HuaBanHelper;
import com.didikee.demos.dao.huaban.HuaBanPop;
import com.didikee.demos.dao.huaban.HuaBanRecyclerAdapter;
import com.didikee.demos.dao.huaban.HuaBanViewHelper;
import com.didikee.demos.dao.huaban.WMRecyclerView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HuaBanActivity extends AppCompatActivity {

    private WMRecyclerView recyclerView;
    private List<String> lists;
    private HuaBanRecyclerAdapter adapter;
    private HuaBanHelper helper;
    private HuaBanPop pop;
    private HuaBanViewHelper viewHelper;
    private HuaBanDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hua_ban);
        setBarStyle();
        initData();
        recyclerView = ((WMRecyclerView) findViewById(R.id.rv));
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL){
            @Override
            public boolean canScrollVertically() {
                return super.canScrollVertically();
            }
        };
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.addItemDecoration(new HuaBanDivier(this));
        adapter = new HuaBanRecyclerAdapter(this,lists);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnPopShowListener(new WMRecyclerView.OnPopShow() {
            @Override
            public void giveYouMyMotion(MotionEvent e) {
                pop.setOtherMotion(e);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if (recyclerView.getWmSHow()){
                    Log.e("test","item: "+e.getRawX());
                    pop.setOtherMotion(e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                if (recyclerView.getWmSHow()){
                    pop.setOtherMotion(e);
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        adapter.setOnClickListener(new HuaBanRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(View view, int postion) {
                Toast.makeText(HuaBanActivity.this,"点击了："+postion,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void ItemLongClickListener(final View view, int postion, float x, float y) {
                Log.e("test","long: x: "+x+"   y: "+y);
                pop.setLocationForLayout(x,y);

//                HuaBanActivity.this.setFinishOnTouchOutside();
                pop.show();
                recyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
//                recyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));

//                pop.setOtherMotion(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));

//                recyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
//                recyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0));

//                pop.setOtherMotion(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
//                recyclerView.setWmShow(true);

            }

        });

        pop = new HuaBanPop(this);

        pop.setPopListener(new HuaBanPop.PopListener() {
            @Override
            public void onDismiss() {
                recyclerView.setWmShow(false);
                recyclerView.setFocusable(true);
//                restRVScroll();
            }
        });
    }

    private void restRVScroll(){
        Class<?> clz = RecyclerView.class;
        try {
            Method resetTouch = clz.getDeclaredMethod("resetTouch");
            resetTouch.setAccessible(true);
            resetTouch.invoke(recyclerView);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("test","执行失败!");
        }
    }

    private void initData() {
        lists = new ArrayList();
        for (int i = 0; i < 100; i++) {
            lists.add("" + i);
        }
    }
    public void setBarStyle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
