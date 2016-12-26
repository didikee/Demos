package com.didikee.demos.ui.act.viewActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.didikee.demos.R;
import com.didikee.demos.dao.huaban.HuaBanDivier;
import com.didikee.demos.dao.huaban.NormalAdapter;
import com.didikee.demos.dao.huaban.TempRV;

import java.util.ArrayList;
import java.util.List;

public class NormalStagleRVActivity extends AppCompatActivity {

    private TempRV mRV;
    private List<String> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_stagle_rv);

        initData();
        mRV = ((TempRV) findViewById(R.id.rv));
        mRV.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRV.addItemDecoration(new HuaBanDivier(this));
        NormalAdapter adapter = new NormalAdapter(this, lists);
        mRV.setAdapter(adapter);
    }
    private void initData() {
        lists = new ArrayList();
        for (int i = 0; i < 100; i++) {
            lists.add("" + i);
        }
    }
}
