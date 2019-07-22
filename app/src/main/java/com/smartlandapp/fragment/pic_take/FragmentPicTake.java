package com.smartlandapp.fragment.pic_take;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartlandapp.R;

public class FragmentPicTake extends Fragment {
    public FragmentPicTake() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pic, container, false);
    }
}
