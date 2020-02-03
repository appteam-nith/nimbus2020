package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nith.appteam.nimbus2020.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileMain extends AppCompatActivity {

    private CircleImageView profilePicture;
    private TextView name, college, phoneNumber, rollno;
    private ImageView editProfile;

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
        phoneNumber.setText(sharedPreferences.getString("phone", "123456890"));
        rollno.setText(sharedPreferences.getString("rollno", "Your roll number"));
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileMain.this, ProfileNew.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
        String imageUrl = sharedPreferences.getString("profileImage", String.valueOf(R.string.defaultImageUrl));
        Toast.makeText(this, imageUrl, Toast.LENGTH_SHORT).show();
        Picasso.with(ProfileMain.this)
                .load(imageUrl)
                .placeholder(R.drawable.default_profile_pic)
                .into(profilePicture);
    }
}
