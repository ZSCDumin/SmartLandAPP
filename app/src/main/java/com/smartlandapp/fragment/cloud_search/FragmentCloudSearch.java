package com.smartlandapp.fragment.cloud_search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartlandapp.R;

public class FragmentCloudSearch extends Fragment {
    public FragmentCloudSearch() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cloud, container, false);
    }
}
