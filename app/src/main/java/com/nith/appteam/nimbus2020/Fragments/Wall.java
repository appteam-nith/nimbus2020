package com.nith.appteam.nimbus2020.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nith.appteam.nimbus2020.Activities.CampusAmbassadorPost;
import com.nith.appteam.nimbus2020.Adapters.FeedRecyclerAdapter;
import com.nith.appteam.nimbus2020.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

public class Wall extends Fragment {
    private SharedPreferences sharedPreferences;
    private FloatingActionButton upload;
    private RecyclerView feed;
    private ArrayList<String> feedList = new ArrayList<>();

    public Wall() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wall, container, false);
        upload = rootView.findViewById(R.id.upload);
        feed = rootView.findViewById(R.id.feed);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        feed.setLayoutManager(layoutManager);
        sharedPreferences = getActivity().getSharedPreferences("app", Context.MODE_PRIVATE);
        Boolean caStatus = sharedPreferences.getBoolean("campusAmbassador", false);
        if (caStatus)
            upload.setVisibility(View.VISIBLE);
        else upload.setVisibility(View.GONE);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), CampusAmbassadorPost.class);
                startActivity(i);
            }
        });

        feed.setAdapter(new FeedRecyclerAdapter(getContext(), feedList));
        getFeeds();
        return rootView;
    }

    private void getFeeds() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(R.string.baseUrl + "/views/links", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String feedUrl = jsonArray.getJSONObject(i).getString("image_url");
                        feedList.add(feedUrl);
                        Objects.requireNonNull(feed.getAdapter()).notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);
    }
}
