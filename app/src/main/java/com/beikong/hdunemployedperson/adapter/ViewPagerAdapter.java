package com.beikong.hdunemployedperson.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


/**
 * Created by Administrator on 2016/12/14.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mList = null;

    public ViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
