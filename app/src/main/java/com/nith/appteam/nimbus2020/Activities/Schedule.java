package com.nith.appteam.nimbus2020.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.nith.appteam.nimbus2020.Fragments.Day1Fragment;
import com.nith.appteam.nimbus2020.Fragments.Day2Fragment;
import com.nith.appteam.nimbus2020.R;
import android.app.Fragment;
import android.widget.Button;

public class Schedule extends AppCompatActivity {

    Button day1Button;
    Button day2Button;
    Button day3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        day1Button=findViewById(R.id.day1Button);
        day2Button=findViewById(R.id.day2Button);
        day3Button=findViewById(R.id.day3Button);
        day1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadFragment(new Day1Fragment());
            }
        });
        day2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadFragment(new Day2Fragment());
            }
        });




    }






    private void LoadFragment(Fragment fragment){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.schedule_frame,fragment);
        fragmentTransaction.commit();
    }

}
