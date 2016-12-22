package com.didikee.demos.dao.miguoMain;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.didikee.demos.R;
import com.didikee.demos.dao.HHRecyclerView.GravitySnapHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by didik 
 * Created time 2016/12/20
 * Description: 
 */

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> strings;
    private final Context context;
    private HashMap<Type,Integer> keepInfo=new HashMap<>();

    public RVAdapter(Context context,List<String> strings) {
        this.strings = strings;
        this.context=context;
        initInfo();
    }

    private void initInfo() {
        keepInfo.put(Type.Hello,0);
        keepInfo.put(Type.Banner,0);
        keepInfo.put(Type.TimeLimit,0);
        keepInfo.put(Type.Sort,0);
        keepInfo.put(Type.Goods,0);
    }

    public enum Type{
        Hello,Banner,TimeLimit,Sort,Goods
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Type.Hello.ordinal()){
            return new ViewHolderHello(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_hello, parent, false));
        }else if (viewType ==Type.Banner.ordinal()){
            return new ViewHolderBanner(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_banner, parent, false));
        }else if (viewType ==Type.TimeLimit.ordinal()){
            return new ViewHolderTimeLimit(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_timelimit, parent, false));
        }else if (viewType ==Type.Sort.ordinal()){
            return new ViewHolderSort(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_sort, parent, false));
        }else {
//            viewType ==Type.Goods.ordinal()
            return new ViewHolderGoods(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_goods, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0){
            ((ViewHolderHello)holder).tv_name.setText(strings.get(position));
        }else if (position == 1){
            ViewPagerAdapter vAdapter=new ViewPagerAdapter();
            ((ViewHolderBanner)holder).viewPager.setAdapter(vAdapter);
        }else if (position == 2){
            TimeLimitAdapter limitAdapter=new TimeLimitAdapter(strings);
            ((ViewHolderTimeLimit)holder).hrv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ((ViewHolderTimeLimit)holder).hrv.setNestedScrollingEnabled(false);
            ((ViewHolderTimeLimit)holder).hrv.setAdapter(limitAdapter);
            GravitySnapHelper snapTimeLimit=new GravitySnapHelper(Gravity.START, false, new GravitySnapHelper.SnapListener() {
                @Override
                public void onSnap(int position) {
                    keepInfo.put(Type.TimeLimit,position);
                }
            });
            try {
                snapTimeLimit.attachToRecyclerView(((ViewHolderTimeLimit)holder).hrv);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Log.e("test","has already attach");
            }
            ((ViewHolderTimeLimit)holder).hrv.scrollToPosition(keepInfo.get(Type.TimeLimit));


        }else if (position == 3){
//            ((ViewHolderSort)holder).hrv.setVisibility(View.GONE);
            TimeLimitAdapter limitAdapter=new TimeLimitAdapter(strings);
            ((ViewHolderSort)holder).hrv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ((ViewHolderSort)holder).hrv.setNestedScrollingEnabled(false);
            ((ViewHolderSort)holder).hrv.setAdapter(limitAdapter);
            GravitySnapHelper snapSort=new GravitySnapHelper(Gravity.START, false, new GravitySnapHelper.SnapListener() {
                @Override
                public void onSnap(int position) {
                    keepInfo.put(Type.Sort,position);
                }
            });
            try {
                snapSort.attachToRecyclerView(((ViewHolderSort)holder).hrv);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                Log.e("test","has already attach");
            }
            ((ViewHolderSort)holder).hrv.scrollToPosition(keepInfo.get(Type.Sort));
        }else {
            ((ViewHolderGoods)holder).tv_name.setText(strings.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        int result;
        if (position == 0){
            result=Type.Hello.ordinal();
        }else if (position == 1){
            result=Type.Banner.ordinal();
        }else if (position == 2){
            result=Type.TimeLimit.ordinal();
        }else if (position == 3){
            result=Type.Sort.ordinal();
        }else {
            result=Type.Goods.ordinal();
        }
        return result;
    }

    @Override
    public int getItemCount() {
        return strings==null ? 0 :strings.size();
    }

    public class ViewHolderGoods extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ViewHolderGoods(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public class ViewHolderHello extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ViewHolderHello(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public class ViewHolderBanner extends RecyclerView.ViewHolder {
        public ViewPager viewPager;
        public ViewHolderBanner(View itemView) {
            super(itemView);
            viewPager= (ViewPager) itemView.findViewById(R.id.viewPager);
        }
    }

    public class ViewHolderTimeLimit extends RecyclerView.ViewHolder {
        public HRecyclerView hrv;
        public ViewHolderTimeLimit(View itemView) {
            super(itemView);
            hrv= (HRecyclerView) itemView.findViewById(R.id.hrv);
        }
    }

    public class ViewHolderSort extends RecyclerView.ViewHolder {
        public RecyclerView hrv;
        public ViewHolderSort(View itemView) {
            super(itemView);
            hrv= (RecyclerView) itemView.findViewById(R.id.hrv);
        }
    }
}
