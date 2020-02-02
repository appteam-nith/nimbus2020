package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.R;

public class MainActivity extends AppCompatActivity {
    private Button quiz, sponsor, profile, campusA, workshops, talks, events, exhibition, schedule;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    //    private CircleImageView profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUI();
//
//        profileButton = findViewById(R.id.profile_button);
//        post = findViewById(R.id.post);
        sharedPref = getSharedPreferences("app", MODE_PRIVATE);
        editor = sharedPref.edit();

        //Checking whether user has logged in or not
//        if (sharedPref.getBoolean("loginStatus", false) == false) {
//            Intent i = new Intent(this, Login.class);
//            startActivity(i);
//            finish();
//        }
//
//        //Checking whether user has created profile or not
//        if (sharedPref.getBoolean("profileStatus", false) == false) {
//            Intent i = new Intent(this, ProfileNew.class);
//            startActivity(i);
//            finish();
//        }

//        Picasso.with(MainActivity.this)
//                .load(sharedPref.getString("imageUrl", String.valueOf(R.string.defaultImageUrl)))
//                .into(profileButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileMain.class);
                startActivity(i);
            }
        });
        campusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CampusAmbassadorPost.class);
                startActivity(i);
            }
        });





        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizMainActivity.class);
                startActivity(intent);
            }
        });







        sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SponsorsActivity.class);
                startActivity(intent);
            }
        });

        workshops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Workshops.class);
                startActivity(intent);
            }
        });
        talks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Talks.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Event_Choose.class);
                startActivity(intent);
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Schedule_Day.class);
                startActivity(intent);
            }
        });
        exhibition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Exhhibition.class);
                startActivity(intent);
            }
        });


    }

    private void getUI() {
        quiz = findViewById(R.id.quiz);
        sponsor = findViewById(R.id.sponsors);
        talks = findViewById(R.id.talks);
        workshops = findViewById(R.id.workshops);
        events = findViewById(R.id.events);
        campusA = findViewById(R.id.ca);
        profile = findViewById(R.id.profile);
        exhibition = findViewById(R.id.exhibition);
        schedule = findViewById(R.id.schedule);

    }
}
