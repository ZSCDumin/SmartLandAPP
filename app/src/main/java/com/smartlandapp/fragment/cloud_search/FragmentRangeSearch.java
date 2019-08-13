package com.smartlandapp.fragment.cloud_search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartlandapp.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

import java.util.ArrayList;

public class FragmentRangeSearch extends Fragment {

    private MapView mapView;
    private MapController mapController;
    private GeoPoint point;
    ArrayList<GeoPoint> list = new ArrayList<GeoPoint>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_range_search, container, false);
        mapView = (MapView) root.findViewById(R.id.map_range);

        /*
        mapView.setBuiltInZoomControls(true);
        //得到mapView的控制权,可以用它控制和驱动平移和缩放
        mapController = mapView.getController();
        //用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
        point = new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
        //设置地图中心点
        mapController.setCenter(point);
        //设置地图zoom级别
        mapController.setZoom(12);

        */
        return root;
    }
}
