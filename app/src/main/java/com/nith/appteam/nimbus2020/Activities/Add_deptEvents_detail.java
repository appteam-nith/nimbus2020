package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.Models.departmentEvent;
import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_deptEvents_detail extends AppCompatActivity {
    private departmentEvent dept;
    private TextView nameDetEventsD, infoDetEventsD, venueDetEventsD, dateDetEventsD;
    private Button regDetEventsD, absEventD;
    private ImageView imgDetEventsD;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private TextView abstractDet;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dept_events_detail);

        dept = (departmentEvent) getIntent().getSerializableExtra("departmentEvents");
        setUpUI();
        getMovDetails();
        regDetEventsD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oprnURLExh(dept.getRegURLDEVE());
            }
        });
        absEventD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogBox();
            }
        });


    }

    private void openDialogBox()
    {  alertDialogBuilder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.abstract_dialog_box,null);
        abstractDet= view.findViewById(R.id.abstract_dialogText);
        abstractDet.setText(dept.getAbstractDEVE());
        Button submit= view.findViewById(R.id.CloseButton);
        alertDialogBuilder.setView(view);
        dialog=alertDialogBuilder.create();
        dialog.show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"exit",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });
    }






    private void oprnURLExh(String regURL) {
        Intent intent = new Intent(Add_deptEvents_detail.this,Web.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("url", regURL);
        getApplicationContext().startActivity(intent);

    }


    private void getMovDetails() {
        if (dept != null) {
            nameDetEventsD.setText(dept.getNameDEVE());
            infoDetEventsD.setText(dept.getInfoDEVE());
            venueDetEventsD.setText(dept.getVenueDEVE());
            dateDetEventsD.setText(dept.getDateDEVE());
            //  typeWo.setText(workshopModel.getTypeWor());
            Picasso.with(getApplicationContext()).load(dept.getImageDEVE()).placeholder(android.R.drawable.ic_btn_speak_now).into(imgDetEventsD);
        }


    }

    private void setUpUI() {

        nameDetEventsD = findViewById(R.id.NameIDDetEventsD);
        infoDetEventsD = findViewById(R.id.InfoIDDetEventsD);
        venueDetEventsD = findViewById(R.id.VenueIDDetEventsD);
        dateDetEventsD = findViewById(R.id.DateDetEventsD);
        regDetEventsD = findViewById(R.id.registerDetEventsD);
        absEventD = findViewById(R.id.abstractDEventsDet);
        imgDetEventsD = findViewById(R.id.ImgDetEventsD);

        //tupeWor=findViewById(R.id.workshopTypeIDDet);
    }
}
