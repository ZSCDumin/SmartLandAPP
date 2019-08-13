package com.smartlandapp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartlandapp.R;
import com.smartlandapp.utility.NotScrollViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 底部菜单栏
 */

public class BottomMenu extends AppCompatActivity {

    /**
     *菜单标题
     */
    private final int[] TAB_TITLES = new int[]{R.string.menu_cloud,
            R.string.menu_pic, R.string.menu_me};
    /**
     * 菜单图标
     */
    private final int[] TAB_IMGS = new int[]{R.drawable.menu_cloud_selector,
            R.drawable.menu_pic_selector, R.drawable.menu_me_selector};

    @BindView(R.id.view_pager)
    NotScrollViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    View view;

    /**
     *页面适配器
     */
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ui_menu);
        ButterKnife.bind(this);

        //初始化页面
        initPager();
        //设置页面效果
        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMGS);
    }

    /**
     * 设置页面显示效果
     * @param tableLayout
     * @param inflater
     * @param tabTitles
     * @param tabImgs
     */
    private void setTabs(TabLayout tableLayout, LayoutInflater inflater, int[] tabTitles, int[] tabImgs) {
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tableLayout.newTab();
            View view = inflater.inflate(R.layout.menu_item, null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitles[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);
        }
    }

    private void initPager() {
        adapter = new MenuFragmentAdapter(getSupportFragmentManager());
        viewPager.setScanScroll(false);
        viewPager.setAdapter(adapter);
        //翻页关联切换
        // viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //TabLayout切换
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消平滑切换
                viewPager.setCurrentItem(tab.getPosition(), false);

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}


