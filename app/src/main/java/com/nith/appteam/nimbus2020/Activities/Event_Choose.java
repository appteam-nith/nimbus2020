package com.nith.appteam.nimbus2020.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nith.appteam.nimbus2020.R;

public class Event_Choose extends AppCompatActivity {
    private Button dept,inst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__choose);
        dept=findViewById(R.id.dptID);
        inst=findViewById(R.id.instID);
        dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Event_Choose.this,department_Events.class);
                startActivity(intent);
            }
        });
        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentI=new Intent(Event_Choose.this,Institute_Events.class);
                startActivity(intentI);

            }
        });
    }
}
