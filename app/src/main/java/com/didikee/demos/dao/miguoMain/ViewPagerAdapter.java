package com.didikee.demos.dao.miguoMain;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.didikee.demos.R;

/**
 * Created by didik 
 * Created time 2016/12/21
 * Description: 
 */

public class ViewPagerAdapter extends PagerAdapter {

    private int[] res=new int[]{
            R.mipmap.test_1,
            R.mipmap.test_2,
            R.mipmap.test_3,
            R.mipmap.test_4
    };
    @Override
    public int getCount() {
        return res.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(container.getContext());
        imageView.setImageResource(res[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
