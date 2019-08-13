package com.smartlandapp.fragment.cloud_search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.smartlandapp.R;
import com.smartlandapp.ui.TopNavigation;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.OnGetPoiResultListener;
import com.tianditu.android.maps.PoiInfo;
import com.tianditu.android.maps.PoiSearch;
import com.tianditu.android.maps.TErrorCode;

import java.util.ArrayList;

/**
 * 显示云查询界面
 */
public class FragmentCloudSearch extends Fragment implements OnGetPoiResultListener{

    private LinearLayout mapChange = null;
    private LinearLayout rangeSearch = null;
    private LinearLayout result = null;
    private LinearLayout myLoc = null;
    private ImageView locSearch = null;
    private EditText editText = null;
    private MapView mapView = null;
    private int clickNum = 0;
    private MyLocationOverlay myLocationOverlay;
    private GeoPoint point;
    private MapController mapController;
    private PoiSearch poiSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //获取云查询页面布局
        View root = inflater.inflate(R.layout.fragment_cloud, container, false);
        //加载天地图
        mapView = (MapView) root.findViewById(R.id.map);

        mapView.setBuiltInZoomControls(true);
        //得到mapView的控制权,可以用它控制和驱动平移和缩放
        mapController = mapView.getController();
        //用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
        point = new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
        //设置地图中心点
        mapController.setCenter(point);
        //设置地图zoom级别
        mapController.setZoom(12);
        return root;
    }

    /**
     * 界面按钮操作
     * （图层切换、范围查询、查询结果、我的位置）
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        //绑定LinearLayout按钮
        InitSetting();
        //添加按钮点击事件
        InitEvent();
    }

    /**
     * 绑定LinearLayout按钮
     */
    private void InitSetting() {
        mapChange = (LinearLayout) getActivity().findViewById(R.id.map_change);
        rangeSearch = (LinearLayout) getActivity().findViewById(R.id.range_search);
        result = (LinearLayout) getActivity().findViewById(R.id.result);
        myLoc = (LinearLayout) getActivity().findViewById(R.id.my_loc);
        locSearch = (ImageView) getActivity().findViewById(R.id.loc_search);
        editText = (EditText) getActivity().findViewById(R.id.loc_input);

    }

    /**
     * 添加按钮点击事件
     */
    private void InitEvent() {
        //图层切换
        mapChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNum = clickNum + 1;
                //TODO Auto-generated method stub
                //切换图层界面
                switch (clickNum) {
                    case 0:
                        mapView.setMapType(MapView.TMapType.MAP_TYPE_VEC);
                        break;
                    case 1:
                        mapView.setMapType(MapView.TMapType.MAP_TYPE_IMG);
                        break;
                    case 2:
                        mapView.setMapType(MapView.TMapType.MAP_TYPE_TERRAIN);
                        break;
                }
                if (clickNum == 2) {
                    clickNum = -1;
                }
            }
        });
        //范围查询
        rangeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.cloud_range_search);
                //跳转至范围查询界面
                Intent intent = new Intent(getActivity(), TopNavigation.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //查询结果
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Auto-generated method stub
                //构造包含相应索引值的Bundle
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.cloud_result);
                //跳转至查询结果界面
                Intent intent = new Intent(getActivity(), TopNavigation.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //我的位置
        myLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //定位当前位置
                myLocationOverlay = new MyLocationOverlay(getActivity(), mapView);
                //启动指南针位置更新
                myLocationOverlay.enableCompass();
                //启用我的位置
                myLocationOverlay.enableMyLocation();
                mapView.addOverlay(myLocationOverlay);
                //获取当前位置
                point = myLocationOverlay.getMyLocation();
                //动画移动到当前位置
                mapController.animateTo(point);
            }
        });
        //手动查询地点
        locSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 对于给定的坐标点,开始动画显示地图
     * @param poiInfo
     * @param cityInfo
     * @param keyword
     * @param error
     */
    @Override
    public void OnGetPoiResult(ArrayList<PoiInfo> poiInfo, ArrayList<PoiSearch.ProvinceInfo> cityInfo, String keyword, int error) {

        int poiSize = poiInfo != null ? poiInfo.size() : 0;
        int citySize = cityInfo != null ? cityInfo.size() : 0;

        if (error != TErrorCode.OK) {
            return;
        }
        if (poiSize == 0 && citySize == 0) {
            return;
        }

        GeoPoint point = poiInfo.get(0).mPoint;
        mapView.getController().animateTo(point);
    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图

    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }


}
