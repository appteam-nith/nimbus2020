package com.nith.appteam.nimbus2020.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nith.appteam.nimbus2020.R;

public class Schedule_Day extends AppCompatActivity {
    private Button day1,day2,day3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule__day);
        day1=findViewById(R.id.Day1ID);
        day2=findViewById(R.id.Day2ID);
        day3=findViewById(R.id.Day3ID);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Schedule_Day.this,Schedule.class);
                startActivity(intent);
            }
        });
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentI=new Intent(Schedule_Day.this,Schedule.class);
                startActivity(intentI);

            }
        });
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentD= new Intent (Schedule_Day.this,Schedule.class);
                startActivity(intentD);
            }
        });
    }
}
