package com.didikee.demos.dao.miguoMain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.didikee.demos.R;

import java.util.List;

/**
 * Created by didik 
 * Created time 2016/12/21
 * Description: 
 */

public class TimeLimitAdapter extends RecyclerView.Adapter<TimeLimitAdapter.ViewHolder> {

    private List<String> strings;

    public TimeLimitAdapter(List<String> strings) {
        this.strings = strings;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_timelimit_inner_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_name.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings ==null ? 0:strings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
