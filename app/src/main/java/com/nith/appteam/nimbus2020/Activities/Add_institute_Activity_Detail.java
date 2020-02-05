package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.Models.instituteEvent;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

public class Add_institute_Activity_Detail extends AppCompatActivity {
    private instituteEvent instituteEvent;
    private TextView nameDetEventsI, infoDetEventsI, venueDetEventsI, dateDetEventsI;
    private Button regDetEventsI, absEventI;
    private ImageView imgDetEventsI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_institute___detail);
        instituteEvent = (instituteEvent) getIntent().getSerializableExtra("instituteEvents");
        setUpUI();
        getMovieDetails();
        regDetEventsI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oprnURLExh(instituteEvent.getRegURLIEVE());
            }
        });
        absEventI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogelLink(instituteEvent.getAbstractIEVE());
            }
        });


    }

    private void openGoogelLink(String abstractIEVE) {
        Uri uri = Uri.parse(abstractIEVE);
        Intent launch = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launch);

    }

    private void oprnURLExh(String regURL) {
        Uri uri = Uri.parse(regURL);
        Intent launch = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launch);
    }

    private void getMovieDetails() {
        if (instituteEvent != null) {
            nameDetEventsI.setText(instituteEvent.getNameIEVE());
            infoDetEventsI.setText(instituteEvent.getInfoIEVE());
            venueDetEventsI.setText(instituteEvent.getVenueIEVE());
            dateDetEventsI.setText(instituteEvent.getDateIEVE());
            //  tupeWo.setText(workshopModel.getTypeWor());
            Picasso.with(getApplicationContext()).load(instituteEvent.getImageIEVE()).placeholder(android.R.drawable.ic_btn_speak_now).into(imgDetEventsI);
        }


    }

    private void setUpUI() {

        nameDetEventsI = findViewById(R.id.NameIDDetEventsI);
        infoDetEventsI = findViewById(R.id.InfoIDDetEventsI);
        venueDetEventsI = findViewById(R.id.VenueIDDetEventsI);
        dateDetEventsI = findViewById(R.id.DateDetEventsI);
        regDetEventsI = findViewById(R.id.registerDetEventsI);
        absEventI = findViewById(R.id.abstractIEventsDet);
        imgDetEventsI = findViewById(R.id.ImgDetEventsI);
    }
}
