package com.smartlandapp.fragment.cloud_search;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.smartlandapp.R;
import com.smartlandapp.ui.TopNavigation;

/**
 * 显示查询结果界面
 */
public class FragmentSearchResult extends Fragment {

    private ScrollView addDataItem;
    private ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_result, null);

        addDataItem = (ScrollView) root.findViewById(R.id.dataItem);
        //加载数据页面
        addViewItem();
        return root;


    }

    /**
     * 加载数据页面
     */
    private void addViewItem() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = (View) inflater
                .inflate(R.layout.result_item, null);
        addDataItem.addView(view);
    }

    /**
     * 点击跳转到结果展示页面
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        constraintLayout = (ConstraintLayout) getActivity().findViewById(R.id.data);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.cloud_result_id);
                Intent intent = new Intent(getActivity(), TopNavigation.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
