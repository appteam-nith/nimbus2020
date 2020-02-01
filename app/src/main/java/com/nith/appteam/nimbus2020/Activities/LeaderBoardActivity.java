package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.nith.appteam.nimbus2020.Adapters.LeaderBoardAdapter;
import com.nith.appteam.nimbus2020.Models.LeaderboardModel;
import com.nith.appteam.nimbus2020.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LeaderBoardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar loadwall;
    LeaderBoardAdapter mLeaderBoardAdapter;
    List<LeaderboardModel> mLeaderboardModelList;
    String quizId;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        quizId = getIntent().getStringExtra("quizId");

        queue = Volley.newRequestQueue(LeaderBoardActivity.this);

        recyclerView = findViewById(R.id.leaderboardRecyclerView);
        loadwall = findViewById(R.id.loadwall);
        mLeaderboardModelList = new ArrayList<>();
        mLeaderBoardAdapter = new LeaderBoardAdapter(mLeaderboardModelList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mLeaderBoardAdapter);
        getData();

    }


    private void getData() {
        mLeaderboardModelList.clear();
        loadwall.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, getString(R.string.baseUrl) + "/quiz/leaderboards",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("response", response);

                            JSONArray players = jsonObject.getJSONArray("players");

                            for (int i = 0; i < players.length(); ++i) {

                                JSONObject player = players.getJSONObject(i);

                                mLeaderboardModelList.add(
                                        new LeaderboardModel(player.getString("name"),
                                                player.getInt("score"), player.getString("image")));
                                mLeaderBoardAdapter.notifyDataSetChanged();
                            }
                            loadwall.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("quizId", quizId);
                return params;
            }

        };

        queue.add(stringRequest);

    }

}
