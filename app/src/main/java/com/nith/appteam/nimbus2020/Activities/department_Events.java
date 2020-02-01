package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.nith.appteam.nimbus2020.Adapters.Events_D_RecyclerViewAdapter;
import com.nith.appteam.nimbus2020.Models.departmentEvent;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;
import com.nith.appteam.nimbus2020.Utils.PrefsDevents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class department_Events extends AppCompatActivity {
    private RecyclerView recyclerViewDEVE;
    private Events_D_RecyclerViewAdapter events_d_recyclerViewAdapter;
    private RequestQueue requestQueueEVED;
    private List<departmentEvent> eventlistD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departmen);
        requestQueueEVED = Volley.newRequestQueue(this);

        recyclerViewDEVE = findViewById(R.id.recyclerViewEVED);
        recyclerViewDEVE.setHasFixedSize(true);
        recyclerViewDEVE.setLayoutManager(new LinearLayoutManager(this));
        eventlistD = new ArrayList<>();
        PrefsDevents prefsDevent = new PrefsDevents(this);
        String search = prefsDevent.getSearch();
        eventlistD = getEventD(search);
        //   talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
        // recyclerView.setAdapter(talkRecyclerViewAdapter);
        //     talkRecyclerViewAdapter.notifyDataSetChanged();

    }

    public List<departmentEvent> getEventD(String searchTerm)//all info returned from api
    {
        eventlistD.clear();
        events_d_recyclerViewAdapter = new Events_D_RecyclerViewAdapter(this, eventlistD);
        recyclerViewDEVE.setAdapter(events_d_recyclerViewAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Constant.Url + searchTerm, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response", response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject talkObj = response.getJSONObject(i);
                        departmentEvent eventD = new departmentEvent();
//                        talk.setName("Aysuh
//                        KAusnldjhlkhfkllnewlfnlwenflkjewlkjfljwhekjksdjkjhkuhkjhkjsdhlehlkjhalhldhll");
//                        talk.setVenue("LEcture
//                        aHAljewnfkljcnkjhfewkkjhefkjwhkfjwkejfhkwehkfhkwejnfkll");
                        //    Ievent.setRegURL("https://github.com/appteam-nith/nimbus2019");
//                        talk.setInfo("HE is
//                        veryhlhfeldijvoikbfewkjbkfjwkejfkjwejeovijoeijvoeijdvoijeoijeovjioejioeijvovjoeidjvlkdsnlkvn jsndoviejoiejvoljkdlkjvoeijvoiejovijdokjdeoivjolj");
//                        talk.setDate("19 2022002345453453453450 2");
                        eventD.setNameDEVE(talkObj.getString("name"));
                        eventD.setDateDEVE("On: " + talkObj.getString("date"));
                        eventD.setImageDEVE(talkObj.getString("image"));
                        eventD.setInfoDEVE(talkObj.getString("info"));
                        eventD.setRegURLDEVE(talkObj.getString("regUrl"));
                        eventD.setVenueDEVE("Venue: " + talkObj.getString("venue"));

                        // Log.d("Talk",talk.getName());
                        //Log.d("date",talk.getDate());
                        eventlistD.add(eventD);
                        events_d_recyclerViewAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.getMessage());

            }
        });
        requestQueueEVED.add(jsonArrayRequest);


        return eventlistD;
    }

}




