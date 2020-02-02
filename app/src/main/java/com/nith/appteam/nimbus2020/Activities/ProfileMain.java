package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileMain extends AppCompatActivity {

    private CircleImageView profilePicture;
    private TextView name, college, phoneNumber, rollno;
    private TextView editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        name = findViewById(R.id.name);
        college = findViewById(R.id.college);
        phoneNumber = findViewById(R.id.phone_number);
        rollno = findViewById(R.id.rollno);
        editProfile = findViewById(R.id.edit_profile);
        profilePicture = findViewById(R.id.profile_picture);
        SharedPreferences sharedPreferences = getSharedPreferences("app", MODE_PRIVATE);
        name.setText(sharedPreferences.getString("name", "Name"));
        college.setText(sharedPreferences.getString("college", "Your college name"));
        phoneNumber.setText(sharedPreferences.getString("phoneNumber", "123456890"));
        rollno.setText(sharedPreferences.getString("rollno", "Your roll number"));
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileMain.this, ProfileNew.class);
                i.putExtra("editProfile", "true");
                startActivity(i);

            }
        });
        Picasso.with(ProfileMain.this)
                .load(sharedPreferences.getString("imageUrl", String.valueOf(R.string.defaultImageUrl)))
                .resize(80, 80)
                .centerCrop()
                .into(profilePicture);


    }
}
