package com.example.me;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
    }
    public void toss_onclick(View view){
        //Intent intent = new Intent(this,)
    }
    public void down_onclick(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    public void change_onclick(View view){
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
    }
    public void find_onclick(View view){

    }
    public void poast1_onclick(View view){

    }
    public void poast2_onclick(View view){

    }
}
