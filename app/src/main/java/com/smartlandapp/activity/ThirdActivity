package com.example.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

    }
    public void r1_onclick(View view){
        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
    }
    public void sure_onclick(View view){
        String DbPassword;
        DbPassword = "123456";
        EditText tishi1 = (EditText)findViewById(R.id.tishi1);
        EditText tishi2 = (EditText)findViewById(R.id.tishi2);
        EditText tishi3 = (EditText)findViewById(R.id.tishi3);
        TextView tishi4 = (TextView)findViewById(R.id.tishi4);
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
}
