package com.didikee.demos.dao.HHRecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by didik 
 * Created time 2016/12/21
 * Description: 
 */

public class MyLinearSnapHelper extends LinearSnapHelper {

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] positions = super.calculateDistanceToFinalSnap(layoutManager, targetView);
        Log.e("test","position1: "+positions[0]);
        Log.e("test","position2: "+positions[1]);
        return positions;
    }
}
