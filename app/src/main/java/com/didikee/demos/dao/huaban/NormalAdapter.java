package com.didikee.demos.dao.huaban;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.didikee.demos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by didik 
 * Created time 2016/12/26
 * Description: 
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.ViewHolder>{
    private List<String> lists;
    private Context context;
    private List<Integer> heights;
    public NormalAdapter(Context context,List<String> lists) {
        this.context = context;
        this.lists = lists;
        getRandomHeight(this.lists);
    }
    private void getRandomHeight(List<String> lists){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int)(200+Math.random()*400));
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.huaban_rv_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTv.setText(lists.get(position));//为控件绑定数据
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;
        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
