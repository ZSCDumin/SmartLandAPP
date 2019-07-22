package com.smartlandapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smartlandapp.fragment.cloud_search.FragmentCloudSearch;
import com.smartlandapp.fragment.me.FragmentMe;
import com.smartlandapp.fragment.pic_take.FragmentPicTake;

/**
 * 底部菜单栏页面匹配器
 */
public class MenuFragmentAdapter extends FragmentPagerAdapter {

    public MenuFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new FragmentCloudSearch();
                break;
            case 1:
                fragment = new FragmentPicTake();
                break;
            case 2:
                fragment = new FragmentMe();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
