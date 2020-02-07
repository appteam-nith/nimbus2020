package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.Models.TalkModel;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

public class Add_talk_details extends AppCompatActivity {
    private TalkModel talkModel;
    private TextView nameDet, infoDet, venueDet, dateDet;
    private Button regDet;
    private ImageView imgDet;
    private String talkID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_talk_details);
        talkModel = (TalkModel) getIntent().getSerializableExtra("talk");
//        talkID=talkModel.getIdTalk();
        setUpUI();
        getMovieDetails();
        regDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oprnURL(talkModel.getRegURL());
            }
        });


    }

    private void oprnURL(String regURL) {
        Intent intent = new Intent(Add_talk_details.this,Web.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", regURL);
        getApplicationContext().startActivity(intent);
    }

    private void getMovieDetails() {
        if (talkModel != null) {
            nameDet.setText(talkModel.getName());
            infoDet.setText(talkModel.getInfo());
            venueDet.setText(talkModel.getVenue());
            dateDet.setText(talkModel.getDate());
            Picasso.with(getApplicationContext()).load(talkModel.getImage()).placeholder(android.R.drawable.ic_btn_speak_now).into(imgDet);
        }


    }

    private void setUpUI() {

        nameDet = findViewById(R.id.speakerNameIDDet);
        infoDet = findViewById(R.id.speakerInfoIDDet);
        venueDet = findViewById(R.id.speakerVenueIDDet);
        dateDet = findViewById(R.id.SpeakerDateDet);
        regDet = findViewById(R.id.registerDet);
        imgDet = findViewById(R.id.talk_ImgDet);
    }
}
