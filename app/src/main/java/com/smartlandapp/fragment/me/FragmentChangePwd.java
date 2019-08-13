package com.smartlandapp.fragment.me;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.smartlandapp.R;

public class FragmentChangePwd extends Fragment {
    private ScrollView addDataItem;
    private ConstraintLayout constraintLayout;
    private Button password_btn=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_changepwd, null);
        return root;
    }
    //界面按钮操作（确认）
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
        password_btn=(Button)getActivity().findViewById(R.id.password_btn);
    }
    /**
     * 添加按钮点击事件
     */
    private void InitEvent() {
        //确认密码
        password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DbPassword;
                DbPassword = "123456";
                EditText tishi1 = (EditText)getActivity().findViewById(R.id.input_nowPwd);
                EditText tishi2 = (EditText)getActivity().findViewById(R.id.input_newPwd);
                EditText tishi3 = (EditText)getActivity().findViewById(R.id.input_checkPwd);
                TextView tishi4 = (TextView)getActivity().findViewById(R.id.output_pwdHint);
                if (tishi1.getText().toString().equals(DbPassword)){
                    if(tishi3.getText().toString().equals(tishi2.getText().toString())){
                        tishi4.setText("修改成功");
                        //DbPassword = tishi3.getText().toString();
                    }
                    else{
                        tishi4.setText("两次密码不一致");
                    }
                }
                else{
                    //原密码输入错误
                    tishi4.setText("原密码输入错误");
                }
            }
        });
    }
}
