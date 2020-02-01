package com.nith.appteam.nimbus2020.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nith.appteam.nimbus2020.Activities.CampusAmbassadorPost;
import com.nith.appteam.nimbus2020.R;

public class wall extends Fragment {
    private FloatingActionButton upload;

    public wall() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wall, container, false);
        upload = rootView.findViewById(R.id.upload);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("app", Context.MODE_PRIVATE);
        //TODO change default to false
        if (sharedPreferences.getBoolean("campusAmbassador", true)) {
            upload.setVisibility(View.VISIBLE);
        }
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CampusAmbassadorPost.class);
                startActivity(i);
            }
        });




        return rootView;
    }
}
