package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.Models.instituteEvent;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

public class Add_institute_Activity_Detail extends AppCompatActivity {
    private instituteEvent instituteEvent;
    private TextView nameDetEventsI, infoDetEventsI, venueDetEventsI, dateDetEventsI;
    private Button regDetEventsI, absEventI;
    private ImageView imgDetEventsI;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private TextView abstractDet;
    private WebView webView;

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
                openDialogBox();
            }
        });


    }

    private void openDialogBox() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.abstract_dialog_box, null);
        abstractDet = view.findViewById(R.id.abstract_dialogText);
        abstractDet.setText(instituteEvent.getAbstractIEVE());
        Button submit = view.findViewById(R.id.CloseButton);
        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "exit", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });
    }

    private void oprnURLExh(String regURL) {
        Intent intent = new Intent(Add_institute_Activity_Detail.this, Web.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", regURL);
        getApplicationContext().startActivity(intent);

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
