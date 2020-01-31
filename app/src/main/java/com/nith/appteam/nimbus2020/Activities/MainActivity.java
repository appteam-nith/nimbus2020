package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.nith.appteam.nimbus2020.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this,QuizMainActivity.class);
        startActivity(intent);
    }








}
