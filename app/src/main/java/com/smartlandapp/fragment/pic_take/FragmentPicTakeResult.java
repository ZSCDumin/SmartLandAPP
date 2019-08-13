package com.smartlandapp.fragment.pic_take;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smartlandapp.R;
import com.smartlandapp.ui.TopNavigation;

public class FragmentPicTakeResult extends Fragment {
    private Button newcreate=null;
    private Button pushintocloud=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragementpic_result,null);
        return root;
    }
    /**
     * 界面按钮操作
     * （新增随手拍，上传至云端）
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
        newcreate = (Button) getActivity().findViewById(R.id.newcreat);
        pushintocloud = (Button) getActivity().findViewById(R.id.pushintocloud);
    }
    /**
     * 添加按钮点击事件
     */
    private void InitEvent() {
        //新增随手拍
        newcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();

            }
        });
            //上传至云端
        pushintocloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("key", R.string.pic_pushtocloud);
                try {
                    Intent intent = new Intent(getActivity(), TopNavigation.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
