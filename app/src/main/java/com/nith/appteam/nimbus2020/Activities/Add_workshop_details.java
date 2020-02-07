package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.Models.WorkshopModel;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

public class Add_workshop_details extends AppCompatActivity {
    private WorkshopModel workshopModel;
    private TextView nameDetWor, infoDetWor, venueDetWor, dateDetWor, tupeWor;
    private Button regDetWOr;
    private ImageView imgDetWor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workshop_details);
        workshopModel = (WorkshopModel) getIntent().getSerializableExtra("workshop");
        setUpUI();
        getMovieDetails();
        regDetWOr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oprnURLWor(workshopModel.getUrlWor());
            }
        });


    }

    private void oprnURLWor(String regURL) {
        Intent intent = new Intent(Add_workshop_details.this,Web.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", regURL);
        getApplicationContext().startActivity(intent);
    }

    private void getMovieDetails() {
        if (workshopModel != null) {
            nameDetWor.setText(workshopModel.getNameWor());
            infoDetWor.setText(workshopModel.getInfoWor());
            venueDetWor.setText(workshopModel.getVenueWor());
            dateDetWor.setText(workshopModel.getDateWor());
            tupeWor.setText(workshopModel.getTypeWor());
            Picasso.with(getApplicationContext()).load(workshopModel.getImageWor()).placeholder(android.R.drawable.ic_btn_speak_now).into(imgDetWor);
        }


    }

    private void setUpUI() {

        nameDetWor = findViewById(R.id.NameIDDetWor);
        infoDetWor = findViewById(R.id.InfoIDDetWor);
        venueDetWor = findViewById(R.id.VenueIDDetWor);
        dateDetWor = findViewById(R.id.DateDetWor);
        regDetWOr = findViewById(R.id.registerDetWor);
        imgDetWor = findViewById(R.id.ImgDetWor);
        tupeWor = findViewById(R.id.workshopTypeIDDet);
    }
}


