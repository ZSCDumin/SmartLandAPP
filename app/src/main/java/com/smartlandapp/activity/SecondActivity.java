package com.example.me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
    }
    public void return1_onclick(View view){
        Intent intent = new Intent(this,FirstActivity.class);
        startActivity(intent);
    }
    public void x1_onclick(View view){

    }
    public void d1_onclick(View view){

    }
    public void x2_onclick(View view){

    }
    public void d2_onclick(View view){

    }
}
