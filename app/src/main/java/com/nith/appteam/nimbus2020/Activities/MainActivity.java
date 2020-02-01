package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nith.appteam.nimbus2020.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button quiz,sponsor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz=findViewById(R.id.quiz);
        sponsor=findViewById(R.id.sponsors);

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,QuizMainActivity.class);
                startActivity(intent);
            }
        });

        sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SponsorsActivity.class);
                startActivity(intent);
            }
        });


    }
}
