package com.nith.appteam.nimbus2020.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nith.appteam.nimbus2020.Models.ExhibitionModel;
import com.nith.appteam.nimbus2020.Models.departmentEvent;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_deptEvents_detail extends AppCompatActivity {
    private departmentEvent dept;
    private TextView nameDetEventsD,infoDetEventsD,venueDetEventsD,dateDetEventsD;
    private Button regDetEventsD,absEventD;
    private CircleImageView imgDetEventsD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dept_events_detail);
        dept = (departmentEvent) getIntent().getSerializableExtra("departmentEvents");
        setUpUI();
        getMovieDetails();
        regDetEventsD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oprnURLExh(dept.getRegURLDEVE());
            }
        });
        absEventD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
openGoogleLink(dept.getAbstractDEVE());
            }
        });


    }

    private void openGoogleLink(String abstractDEVE) {
        Uri uri = Uri.parse(abstractDEVE);
        Intent launch = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launch);

    }

    private void oprnURLExh(String regURL) {
        Uri uri = Uri.parse(regURL);
        Intent launch = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launch);
    }

    private void getMovieDetails() {
        if (dept != null) {
            nameDetEventsD.setText(dept.getNameDEVE());
            infoDetEventsD.setText(dept.getInfoDEVE());
            venueDetEventsD.setText(dept.getVenueDEVE());
            dateDetEventsD.setText(dept.getDateDEVE());
            //  tupeWo.setText(workshopModel.getTypeWor());
            Picasso.with(getApplicationContext()).load(dept.getImageDEVE()).placeholder(android.R.drawable.ic_btn_speak_now).into(imgDetEventsD);
        }


    }

    private void setUpUI() {

        nameDetEventsD = findViewById(R.id.NameIDDetEventsD);
        infoDetEventsD = findViewById(R.id.InfoIDDetEventsD);
        venueDetEventsD = findViewById(R.id.VenueIDDetEventsD);
        dateDetEventsD = findViewById(R.id.DateDetEventsD);
        regDetEventsD = findViewById(R.id.registerDetEventsD);
        absEventD=findViewById(R.id.abstractDEventsDet);
        imgDetEventsD = findViewById(R.id.ImgDetEventsD);

        //tupeWor=findViewById(R.id.workshopTypeIDDet);
    }
}
