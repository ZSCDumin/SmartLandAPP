package com.smartlandapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartlandapp.R;
import com.smartlandapp.fragment.cloud_search.FragmentRangeSearch;
import com.smartlandapp.fragment.cloud_search.FragmentSearchResult;
import com.smartlandapp.fragment.cloud_search.FragmentShowResult;
import com.smartlandapp.fragment.me.FragmentChangePwd;
import com.smartlandapp.fragment.me.FragmentChecktask;
import com.smartlandapp.fragment.me.FragmentDownload;
import com.smartlandapp.fragment.me.Fragmentabout;
import com.smartlandapp.fragment.pic_take.FragmentPicTakeResult;
import com.smartlandapp.fragment.pic_take.FragmentPushToCloud;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首部导航栏
 */
public class TopNavigation extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout frameLayout;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.back)
    LinearLayout back;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏默认标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.ui_navigation);
        ButterKnife.bind(this);

        //获取点击事件对应的页面布局
        putPage();
        //设置返回监听事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 页面布局选择
     */
    private void putPage() {
        //获取从Fragment传递过来的参数Bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int value = bundle.getInt("key");
        switch (value) {
            case R.string.pic_result:
                FragmentPicTakeResult Result = new FragmentPicTakeResult();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!Result.isAdded()) {
                    transaction.add(R.id.fragment_container, Result);
                    transaction.commit();
                    title.setText("拍摄结果");
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.cloud_result:
                FragmentSearchResult searchConsult = new FragmentSearchResult();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!searchConsult.isAdded()) {
                    transaction.add(R.id.fragment_container, searchConsult);
                    transaction.commit();
                    title.setText(R.string.cloud_result);
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.cloud_result_id:
                FragmentShowResult showResult = new FragmentShowResult();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!showResult.isAdded()) {
                    transaction.add(R.id.fragment_container, showResult);
                    transaction.commit();
                    title.setText("NO.1");
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.me_change_pwd:
                FragmentChangePwd change = new FragmentChangePwd();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!change.isAdded()) {
                    transaction.add(R.id.fragment_container, change);
                    transaction.commit();
                    title.setText("修改密码");
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.me_download:
                FragmentDownload download = new FragmentDownload();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!download.isAdded()) {
                    transaction.add(R.id.fragment_container, download);
                    transaction.commit();
                    title.setText("下载天地图");
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.me_checktask:
                FragmentChecktask check = new FragmentChecktask();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!check.isAdded()) {
                    transaction.add(R.id.fragment_container, check);
                    transaction.commit();
                    title.setText("核查任务");
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.me_about:
                Fragmentabout about = new Fragmentabout();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!about.isAdded()) {
                    transaction.add(R.id.fragment_container, about);
                    transaction.commit();
                    title.setText("关于");
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.cloud_range_search:
                FragmentRangeSearch rangeSearch = new FragmentRangeSearch();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!rangeSearch.isAdded()) {
                    transaction.add(R.id.fragment_container, rangeSearch);
                    transaction.commit();
                    title.setText(R.string.cloud_range_search);
                    transaction.addToBackStack(null);
                }
                break;
            case R.string.pic_pushtocloud:
                FragmentPushToCloud pushToCloud = new FragmentPushToCloud();
                transaction = getSupportFragmentManager().beginTransaction();
                if (!pushToCloud.isAdded()) {
                    transaction.add(R.id.fragment_container, pushToCloud);
                    transaction.commit();
                    title.setText("");
                    transaction.addToBackStack(null);
                }
                break;
            default:
                break;
        }
    }
}
