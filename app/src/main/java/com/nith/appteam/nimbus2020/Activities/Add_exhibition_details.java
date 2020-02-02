package com.nith.appteam.nimbus2020.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nith.appteam.nimbus2020.Models.ExhibitionModel;
import com.nith.appteam.nimbus2020.Models.TalkModel;
import com.nith.appteam.nimbus2020.Models.WorkshopModel;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_exhibition_details extends AppCompatActivity {
    private ExhibitionModel exhibitionModel;
    private TextView nameDetExh,infoDetExh,venueDetExh,dateDetExh;
    private Button regDetExh;
    private CircleImageView imgDetExh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exhibition_details);
        exhibitionModel=(ExhibitionModel) getIntent().getSerializableExtra("exhibition");
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
        Uri uri=Uri.parse(regURL);
        Intent launch= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(launch);
    }

    private void getMovieDetails() {
        if(exhibitionModel!=null) {
            nameDetExh.setText(exhibitionModel.getNameExh());
            infoDetExh.setText(exhibitionModel.getInfoExh());
            venueDetExh.setText(exhibitionModel.getVenueExh());
            dateDetExh.setText(exhibitionModel.getDateExh());
          //  tupeWo.setText(workshopModel.getTypeWor());
            Picasso.with(getApplicationContext()).load(exhibitionModel.getImageExh()).placeholder(android.R.drawable.ic_btn_speak_now).into(imgDetExh);
        }


    }

    private void setUpUI() {

        nameDetExh=findViewById(R.id.NameIDDetExh);
        infoDetExh=findViewById(R.id.InfoIDDetExh);
        venueDetExh=findViewById(R.id.VenueIDDetExh);
        dateDetExh=findViewById(R.id.DateDetExh);
        regDetExh=findViewById(R.id.registerDetExh);
        imgDetExh=findViewById(R.id.ImgDetExh);
        //tupeWor=findViewById(R.id.workshopTypeIDDet);
    }
}

