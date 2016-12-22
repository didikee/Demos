package com.didikee.demos.ui.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.didikee.demos.R;
import com.didikee.demos.dao.miguoMain.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class MiGuoActivity extends AppCompatActivity {

    private RecyclerView RV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_guo);


        RV = ((RecyclerView) findViewById(R.id.rv));

        RV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        RV.setHasFixedSize(true);

        RVAdapter rvAdapter=new RVAdapter(this,getStrings());

        RV.setAdapter(rvAdapter);
    }

    private List<String> getStrings(){
        List<String> strings=new ArrayList<>();
        strings.add("你看,是谁在装逼,第 1 个!");
        strings.add("你看,是谁在装逼,第 2 个!");
        strings.add("你看,是谁在装逼,第 3 个!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,我的哥哟!");
        strings.add("你看,是谁在装逼,最后一个");
        return strings;
    }
}
