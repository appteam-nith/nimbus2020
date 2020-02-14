package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

public class Event_Choose extends AppCompatActivity {
    private Button dept, inst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__choose);

        ImageView img = findViewById(R.id.eventForgrnd);
        Picasso.with(this).load(R.drawable.event).fit().into(img);


        dept = findViewById(R.id.dptID);
        inst = findViewById(R.id.instID);
        dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Event_Choose.this, department_Events.class);
                startActivity(intent);
            }
        });
        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentI = new Intent(Event_Choose.this, Institute_Events.class);
                startActivity(intentI);

            }
        });
    }
}
