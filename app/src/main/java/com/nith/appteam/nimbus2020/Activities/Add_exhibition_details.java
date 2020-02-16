package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.Models.ExhibitionModel;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

public class Add_exhibition_details extends AppCompatActivity {
    private ExhibitionModel exhibitionModel;
    private TextView nameDetExh, infoDetExh, venueDetExh, dateDetExh;
    private Button regDetExh;
    private ImageView imgDetExh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exhibition_details);
        exhibitionModel = (ExhibitionModel) getIntent().getSerializableExtra("exhibition");
        setUpUI();
        getMovieDetails();
        regDetExh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oprnURLExh(exhibitionModel.getRegURLExh());
            }
        });


    }

    private void oprnURLExh(String regURL) {
        Intent intent = new Intent(Add_exhibition_details.this, Web.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", regURL);
        startActivity(intent);
        overridePendingTransition(R.anim.ease_in, R.anim.ease_out);
    }

    private void getMovieDetails() {
        if (exhibitionModel != null) {
            nameDetExh.setText(exhibitionModel.getNameExh());
            infoDetExh.setText(exhibitionModel.getInfoExh());
            venueDetExh.setText(exhibitionModel.getVenueExh());
            dateDetExh.setText(exhibitionModel.getDateExh());
            //  tupeWo.setText(workshopModel.getTypeWor());
            Picasso.with(getApplicationContext()).load(exhibitionModel.getImageExh()).placeholder(android.R.drawable.ic_btn_speak_now).into(imgDetExh);
        }


    }

    private void setUpUI() {

        nameDetExh = findViewById(R.id.NameIDDetExh);
        infoDetExh = findViewById(R.id.InfoIDDetExh);
        venueDetExh = findViewById(R.id.VenueIDDetExh);
        dateDetExh = findViewById(R.id.DateDetExh);
        regDetExh = findViewById(R.id.registerDetExh);
        imgDetExh = findViewById(R.id.ImgDetExh);
        //tupeWor=findViewById(R.id.workshopTypeIDDet);
    }
}

