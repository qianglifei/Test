package com.beikong.hdunemployedperson.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.beikong.hdunemployedperson.CustomView.PieGraphView;
import com.beikong.hdunemployedperson.CustomView.PieItemBean;
import com.beikong.hdunemployedperson.R;

/**
 * Created by Administrator on 2016/12/14.
 */

public class CircleGraphFragment extends Fragment implements View.OnClickListener{

    private HorizontalScrollView mHorizontalScrollView;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioButton radioButton5;
    private RadioButton radioButton6;
    private RadioButton radioButton7;


    private int screenWidthMiddleValue = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View customView = inflater.inflate(R.layout.fragment_circle_graph,container,false);
        initView(customView);
        return customView;
    }

    private void initView(View customView) {
        mHorizontalScrollView = (HorizontalScrollView) customView.findViewById(R.id.horizontalScrollView);


        radioButton1 = (RadioButton) customView.findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) customView.findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) customView.findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) customView.findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) customView.findViewById(R.id.radioButton5);
        radioButton6 = (RadioButton) customView.findViewById(R.id.radioButton6);
        radioButton7 = (RadioButton) customView.findViewById(R.id.radioButton7);

        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);
        radioButton4.setOnClickListener(this);
        radioButton5.setOnClickListener(this);
        radioButton6.setOnClickListener(this);
        radioButton7.setOnClickListener(this);

        screenWidthMiddleValue = getScreenMiddleValue();
    }
    PieItemBean pieItems[];
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radioButton1:
                mHorizontalScrollView.smoothScrollTo(0,0);
                resetTimeLine();
                radioButton1.setTextColor(Color.WHITE);
                radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.balloon,0,0);

                PieGraphView pieGraphView = (PieGraphView) getActivity().findViewById(R.id.pieGraphView);

                pieItems = new PieItemBean[]{
                        new PieItemBean(80,"充分就业"),
                        new PieItemBean(20,"非就业"),
                };

                pieGraphView.setPieItems(pieItems);

                pieGraphView.setOnSpecialTypeClickListener(new PieGraphView.OnSpecialTypeClickListener() {
                    @Override
                    public void onSpecialTypeClick(String type) {
                        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.radioButton2:
                mHorizontalScrollView.smoothScrollTo(0,0);
                resetTimeLine();
                radioButton2.setTextColor(Color.WHITE);
                radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.balloon,0,0);
                PieGraphView pieGraphView2 = (PieGraphView)  getActivity().findViewById(R.id.pieGraphView);
                pieItems = new PieItemBean[]{
                        new PieItemBean(80,"充分就业"),
                        new PieItemBean(20,"非就业"),
                };
                pieGraphView2.setPieItems(pieItems);

                pieGraphView2.setOnSpecialTypeClickListener(new PieGraphView.OnSpecialTypeClickListener() {
                    @Override
                    public void onSpecialTypeClick(String type) {
                        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.radioButton3:
                mHorizontalScrollView.smoothScrollTo(242, 0);
                resetTimeLine();
                radioButton3.setTextColor(Color.WHITE);
                radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.balloon,0,0);
                PieGraphView pieGraphView3 = (PieGraphView) getActivity().findViewById(R.id.pieGraphView);
                pieItems = new PieItemBean[]{
                        new PieItemBean(80,"充分就业"),
                        new PieItemBean(20,"非就业"),
                };
                pieGraphView3.setPieItems(pieItems);

                pieGraphView3.setOnSpecialTypeClickListener(new PieGraphView.OnSpecialTypeClickListener() {
                    @Override
                    public void onSpecialTypeClick(String type) {
                        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.radioButton4:
                mHorizontalScrollView.smoothScrollTo(515,0);
                resetTimeLine();
                radioButton4.setTextColor(Color.WHITE);
                radioButton4.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.balloon,0,0);

                PieGraphView pieGraphView4 = (PieGraphView) getActivity().findViewById(R.id.pieGraphView);
                pieItems = new PieItemBean[]{
                        new PieItemBean(80,"充分就业"),
                        new PieItemBean(20,"非就业"),
                };
                pieGraphView4.setPieItems(pieItems);
                pieGraphView4.setOnSpecialTypeClickListener(new PieGraphView.OnSpecialTypeClickListener() {
                    @Override
                    public void onSpecialTypeClick(String type) {
                        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.radioButton5:
                mHorizontalScrollView.smoothScrollTo(798,0);
                resetTimeLine();
                radioButton5.setTextColor(Color.WHITE);
                radioButton5.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.balloon,0,0);

                PieGraphView pieGraphView5 = (PieGraphView) getActivity().findViewById(R.id.pieGraphView);
                pieItems = new PieItemBean[]{
                        new PieItemBean(80,"充分就业"),
                        new PieItemBean(20,"非就业"),
                };
                pieGraphView5.setPieItems(pieItems);

                pieGraphView5.setOnSpecialTypeClickListener(new PieGraphView.OnSpecialTypeClickListener() {
                    @Override
                    public void onSpecialTypeClick(String type) {
                        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.radioButton6:
                mHorizontalScrollView.smoothScrollTo(1071,0);
                resetTimeLine();
                radioButton6.setTextColor(Color.WHITE);
                radioButton6.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.balloon,0,0);

                PieGraphView pieGraphView6 = (PieGraphView) getActivity().findViewById(R.id.pieGraphView);
                pieItems = new PieItemBean[]{
                        new PieItemBean(80,"充分就业"),
                        new PieItemBean(20,"非就业"),
                };
                pieGraphView6.setPieItems(pieItems);

                pieGraphView6.setOnSpecialTypeClickListener(new PieGraphView.OnSpecialTypeClickListener() {
                    @Override
                    public void onSpecialTypeClick(String type) {
                        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.radioButton7:
                mHorizontalScrollView.smoothScrollTo(2000,0);
                resetTimeLine();
                radioButton7.setTextColor(Color.WHITE);
                radioButton7.setCompoundDrawablesWithIntrinsicBounds(0,R.mipmap.balloon,0,0);

                PieGraphView pieGraphView7 = (PieGraphView) getActivity().findViewById(R.id.pieGraphView);
                pieItems = new PieItemBean[]{
                        new PieItemBean(80,"充分就业"),
                        new PieItemBean(20,"非就业"),
                };
                pieGraphView7.setPieItems(pieItems);

                pieGraphView7.setOnSpecialTypeClickListener(new PieGraphView.OnSpecialTypeClickListener() {
                    @Override
                    public void onSpecialTypeClick(String type) {
                        Toast.makeText(getContext(),type,Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    private void resetTimeLine() {

        radioButton1.setTextColor(Color.parseColor("#c9e5e1"));
        radioButton2.setTextColor(Color.parseColor("#c9e5e1"));
        radioButton3.setTextColor(Color.parseColor("#c9e5e1"));
        radioButton4.setTextColor(Color.parseColor("#c9e5e1"));
        radioButton5.setTextColor(Color.parseColor("#c9e5e1"));
        radioButton6.setTextColor(Color.parseColor("#c9e5e1"));
        radioButton7.setTextColor(Color.parseColor("#c9e5e1"));

        radioButton1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.oval,0,0);
        radioButton2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.oval,0,0);
        radioButton3.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.oval,0,0);
        radioButton4.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.oval,0,0);
        radioButton5.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.oval,0,0);
        radioButton6.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.oval,0,0);
        radioButton7.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.oval,0,0);
    }

    /**
     * 获取屏幕宽度中间的值
     * @return
     */

    public int getScreenMiddleValue() {
        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int middleValue = width / 2;
        return middleValue;
    }
}
