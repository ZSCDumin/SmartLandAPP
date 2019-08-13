package com.smartlandapp.fragment.pic_take;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartlandapp.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

public class FragmentPushToCloud extends Fragment {

    // 随底部标签切换的Fragment
    private Fragment myFragment1, myFragment2,currentFragment;
    // 底部标签的文本
    private TextView tab_1, tab_2;
    private MapView mapView;
    //地图控件
    private GeoPoint point;
    private MapController mapController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragmentpic_pushtocloud,null);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        //绑定LinearLayout按钮
        InitSetting();
        //添加按钮点击事件
        InitEvent();
        //初始化地图
        InitMap();
    }

    /**
     * 绑定LinearLayout按钮
     */
    private void InitSetting() {
        mapView = (MapView)getActivity().findViewById(R.id.map);
        tab_1 = (TextView) getActivity().findViewById(R.id.tv_tab1);
        tab_2 = (TextView) getActivity().findViewById(R.id.tv_tab2);
    }

    /**
     * 初始化地图
     */
    private void InitMap(){
        mapView.setBuiltInZoomControls(true);
        //得到mapView的控制权,可以用它控制和驱动平移和缩放
        mapController = mapView.getController();
        //用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
        point = new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
        //设置地图中心点
        mapController.setCenter(point);
        //设置地图zoom级别
        mapController.setZoom(12);
    }

    /**
     * 添加按钮点击事件
     */
    private void InitEvent() {
        tab_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment1();
            }
        });
        tab_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment2();
            }
        });
    }

    /**
     * 点击第一个tab
     */
    private void showFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (myFragment1 == null) {
            myFragment1 = new Fragmenthere();
            transaction.add(R.id.content_layout,myFragment1);
        }
        //隐藏所有fragment
        hideAllFragment(transaction);
        //显示需要显示的fragment
        transaction.show(myFragment1);
        //提交事务
        transaction.commit();
        // 设置底部tab变化
        tab_1.setBackgroundColor(Color.RED);
        tab_2.setBackgroundColor(Color.WHITE);
    }

    /**
     * 点击第二个tab
     */
    private void showFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (myFragment2 == null) {
            myFragment2 = new FragmentPicTakeDescription();
            transaction.add(R.id.content_layout,myFragment2);
        }
        //隐藏所有fragment
        hideAllFragment(transaction);
        //显示需要显示的fragment
        transaction.show(myFragment2);
        //提交事务
        transaction.commit();
        // 设置底部tab变化
        tab_1.setBackgroundColor(Color.WHITE);
        tab_2.setBackgroundColor(Color.RED);
    }

    //隐藏所有的fragment
    private void hideAllFragment(FragmentTransaction transaction){
        if(myFragment1 != null){
            transaction.hide(myFragment1);
        }
        if(myFragment2 != null){
            transaction.hide(myFragment2);
        }
    }
}
