package com.beikong.hdunemployedperson.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beikong.hdunemployedperson.R;
import com.beikong.hdunemployedperson.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/13.
 */

public class StatisticsFragment extends Fragment{

    private ViewPager viewPager;
    private ViewPagerAdapter adapter = null;
    private List<Fragment> totalFragment = new ArrayList<>();

    private CircleGraphFragment circleGraphFragment = null;
    private TownIndicatorFragment townIndicatorFragment = null;
    private VillageIndicatorFragment villageIndicatorFragment = null;
    private RosterFragment rosterFragment = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentViewPager();
    }

    private void initFragmentViewPager() {

        circleGraphFragment = new CircleGraphFragment();
        townIndicatorFragment = new TownIndicatorFragment();
        villageIndicatorFragment = new VillageIndicatorFragment();
        rosterFragment = new RosterFragment();

        totalFragment.add(circleGraphFragment);
        totalFragment.add(townIndicatorFragment);
        totalFragment.add(villageIndicatorFragment);
        totalFragment.add(rosterFragment);

        adapter = new ViewPagerAdapter(getFragmentManager(),totalFragment);

        viewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);
    }
}
