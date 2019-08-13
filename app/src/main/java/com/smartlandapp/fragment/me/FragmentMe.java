package com.smartlandapp.fragment.me;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.smartlandapp.R;
import com.smartlandapp.activity.LoginActivity;
import com.smartlandapp.ui.TopNavigation;

public class FragmentMe extends Fragment {
    private LinearLayout namelan = null;
    private LinearLayout xiazailan = null;
    private LinearLayout xiugailan = null;
    private LinearLayout guanyulan = null;
    private LinearLayout jianchalan = null;
    private Button left_btn = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }
    /**
     * 界面按钮操作
     * （下载天地图，修改密码，核查任务，关于，退出）
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
        xiazailan = (LinearLayout) getActivity().findViewById(R.id.xiazailan);
        xiugailan = (LinearLayout) getActivity().findViewById(R.id.xiugailan);
        guanyulan = (LinearLayout) getActivity().findViewById(R.id.guanyulan);
        jianchalan = (LinearLayout) getActivity().findViewById(R.id.jianchalan);
        left_btn = (Button) getActivity().findViewById(R.id.left_btn1);

    }
    /**
     * 添加按钮点击事件
     */
    private void InitEvent() {
        //下载天地图
        xiazailan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.me_download);
                //跳转至下载地图界面
                Intent intent = new Intent(getActivity(), TopNavigation.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        //修改密码
        xiugailan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.me_change_pwd);
                //跳转至修改密码界面
                Intent intent = new Intent(getActivity(), TopNavigation.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        //核查任务
        jianchalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.me_checktask);
                //跳转至核查任务界面
                Intent intent = new Intent(getActivity(), TopNavigation.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        //关于
        guanyulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.me_about);
                //跳转关于界面
                Intent intent = new Intent(getActivity(), TopNavigation.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        //退出账号按钮
        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出账号按钮
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }


}
