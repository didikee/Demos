package com.didikee.demos.dao.HHRecyclerView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.didikee.demos.R;
import com.didikee.demos.utils.ColorUtil;

/**
 * Created by didik 
 * Created time 2016/12/21
 * Description: 
 */

public class HHAdapter extends RecyclerView.Adapter<HHAdapter.ViewHolder> {

    @Override
    public HHAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_hh_item,parent,false));
    }

    @Override
    public void onBindViewHolder(HHAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(position+1+"");
        holder.itemView.setBackgroundColor(Color.parseColor(ColorUtil.random()));
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
