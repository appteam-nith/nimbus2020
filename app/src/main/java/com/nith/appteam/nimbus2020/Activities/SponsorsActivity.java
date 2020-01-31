package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;

import com.nith.appteam.nimbus2020.Adapters.SponsorsAdapter;
import com.nith.appteam.nimbus2020.Models.Sponsor;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.IResult;
import com.nith.appteam.nimbus2020.Utils.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SponsorsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar loadwall;
    SponsorsAdapter mSponsorsAdapter;
    List<Sponsor> mSponsorList;

    private IResult mResultCallback;
    private VolleyService mVolleyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        recyclerView = findViewById(R.id.sponsorsRecyclerView);
        loadwall = findViewById(R.id.loadwall);
        mSponsorList=new ArrayList<>();
        getData();
        mSponsorsAdapter = new SponsorsAdapter(mSponsorList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(mSponsorsAdapter);

    }


    private void getData() {
        loadwall.setVisibility(View.VISIBLE);

        initVolleyCallback();

        mVolleyService = new VolleyService(mResultCallback, this);


        final VolleyService mVolleyService = new VolleyService(mResultCallback, this);

        mVolleyService.getJsonArrayDataVolley("GETSPONSORS",
                getString(R.string.baseUrl) + "/sponsor");

    }


    void initVolleyCallback() {
        mResultCallback = new IResult() {
            JSONObject obj;

            @Override
            public void notifySuccess(String requestType, JSONObject response,
                    JSONArray jsonArray) {


                if (response != null) {

                    Log.e("Hellcatt", response.toString());
                    //JsonObject
                    // Toast.makeText(getContext(), String.valueOf(response), Toast.LENGTH_SHORT)
                    // .show();
                } else {
                    Log.e("zHell", jsonArray.toString());

//                    //JsonArray
//                    Toast.makeText(getContext(), String.valueOf(jsonArray), Toast.LENGTH_SHORT)
//                    .show();


                    for (int i = 0; i < jsonArray.length(); i++) {

                        try {
                            obj = jsonArray.getJSONObject(i);
                            //TODO: parse objects and add to list,call datasetchanged


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.i("error", error.toString());
            }
        };

    }
}
