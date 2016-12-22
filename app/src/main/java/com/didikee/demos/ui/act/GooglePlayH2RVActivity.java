package com.didikee.demos.ui.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.didikee.demos.R;
import com.didikee.demos.dao.HHRecyclerView.HHAdapter;
import com.didikee.demos.dao.HHRecyclerView.HHRecyclerView;

public class GooglePlayH2RVActivity extends AppCompatActivity {

    private HHRecyclerView h2Rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_play_h2_rv);

        h2Rv = ((HHRecyclerView) findViewById(R.id.h2rv));

        HHAdapter hhAdapter=new HHAdapter();


        h2Rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

//        GravitySnapHelper gravitySnapHelper=new GravitySnapHelper(Gravity.START, false, new GravitySnapHelper.SnapListener() {
//
//
//            @Override
//            public void onSnap(int position) {
//                Log.e("test","position: "+position);
//            }
//        });

//        SnapHelper pagerSnapHelper=new PagerSnapHelper();
//        pagerSnapHelper.attachToRecyclerView(h2Rv);
//        gravitySnapHelper.attachToRecyclerView(h2Rv);
        h2Rv.setAdapter(hhAdapter);

    }
}
