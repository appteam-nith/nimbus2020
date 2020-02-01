package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private CircleImageView profileButton;
    private Button post;

    Button quiz,sponsor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Required for cloudinary
        Map config = new HashMap();
        config.put("cloud_name", "duutozrvz");
        MediaManager.init(MainActivity.this, config);
        profileButton = findViewById(R.id.profile_button);
        post = findViewById(R.id.post);
        sharedPref = getSharedPreferences("app", MODE_PRIVATE);
        editor = sharedPref.edit();
        //Checking whether user has logged in or not
//        if (!sharedPref.getBoolean("loginStatus", false)) {
//            Intent i = new Intent(this, Login.class);
//            startActivity(i);
//            finish();
//        }

        //Checking whether user has created profile or not
//        if (!sharedPref.getBoolean("profileStatus", false)) {
//            Intent i = new Intent(this, ProfileNew.class);
//            startActivity(i);
//            finish();
//        }

        Picasso.with(MainActivity.this)



                .load(sharedPref.getString("imageUrl", String.valueOf(R.string.defaultImageUrl)))
                .resize(80, 80)
                .centerCrop()
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
         Button talk,event,workshp,exhibition;
         talk=findViewById(R.id.talkIDX);
         event=findViewById(R.id.EventsIDX);
         workshp=findViewById(R.id.WorkshopIDX);
         exhibition=findViewById(R.id.EventsIDX);
         talk.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity.this,Talks.class);
                 startActivity(intent);


             }
         });
         event.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity.this,Event_Choose.class);
                 startActivity(intent);

             }
         });
         workshp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity.this,Workshops.class);
                 startActivity(intent);
             }
         });
         exhibition.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity.this,Exhhibition.class);
                 startActivity(intent);
             }
         });






    }
}
