package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    Button quiz, sponsor;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private CircleImageView profileButton;
    private Button post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton = findViewById(R.id.profile_button);
        post = findViewById(R.id.post);
        sharedPref = getSharedPreferences("app", MODE_PRIVATE);
        editor = sharedPref.edit();

        //Checking whether user has logged in or not
        if (sharedPref.getBoolean("loginStatus", false) == false) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        //Checking whether user has created profile or not
        if (sharedPref.getBoolean("profileStatus", false) == false) {
            Intent i = new Intent(this, ProfileNew.class);
            startActivity(i);
            finish();
        }

        Picasso.with(MainActivity.this)
                .load(sharedPref.getString("imageUrl", String.valueOf(R.string.defaultImageUrl)))
                .into(profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileMain.class);
                startActivity(i);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CampusAmbassadorPost.class);
                startActivity(i);
            }
        });

        quiz = findViewById(R.id.quiz);
        sponsor = findViewById(R.id.sponsors);

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


    }








}
