package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.R;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("app", MODE_PRIVATE);
        editor = sharedPref.edit();
        //Checking whether user has logged in or not
        if (!sharedPref.getBoolean("loginStatus", false)) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        //Checking whether user has created profile or not
//        if(sharedPref.getBoolean("profileCreated",false)){
//
//        }
        Intent intent = new Intent(this, QuizMainActivity.class);
        startActivity(intent);
    }
}
