package com.nith.appteam.nimbus2020.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nith.appteam.nimbus2020.Adapters.ScheduleRecyclerViewAdapter;
import com.nith.appteam.nimbus2020.Adapters.TalkRecyclerViewAdapter;
import com.nith.appteam.nimbus2020.Models.ScheduleModel;
import com.nith.appteam.nimbus2020.Models.TalkModel;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;
import com.nith.appteam.nimbus2020.Utils.PrefsSchedule;
import com.nith.appteam.nimbus2020.Utils.PrefsTalk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity {
    private RecyclerView recyclerViewSch;
    private List<ScheduleModel> scheduleModelList;
    private ScheduleRecyclerViewAdapter scheduleRecyclerViewAdapter;
    private RequestQueue requestQueueSch;
    private ProgressBar loadWall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        loadWall=findViewById(R.id.loadwallSch);
        requestQueueSch= Volley.newRequestQueue(this);
        recyclerViewSch= findViewById(R.id.recyclerViewSchedule);
        recyclerViewSch.setHasFixedSize(true);
        recyclerViewSch.setLayoutManager( new LinearLayoutManager(this));
        scheduleModelList= new ArrayList<>();
        PrefsSchedule prefsschedule=new PrefsSchedule(this);
        String search=prefsschedule.getSearch();
        scheduleModelList=getSchedule(search);
        //   talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
        // recyclerView.setAdapter(talkRecyclerViewAdapter);
        //     talkRecyclerViewAdapter.notifyDataSetChanged();

    }
    public List<ScheduleModel> getSchedule(String searchTerm)//all info returned from api
    {
        loadWall.setVisibility(View.VISIBLE);
        scheduleModelList.clear();
        scheduleRecyclerViewAdapter=new ScheduleRecyclerViewAdapter(this,scheduleModelList);
        recyclerViewSch.setAdapter(scheduleRecyclerViewAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constant.Url+ searchTerm,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loadWall.setVisibility(View.GONE);
                Log.d("Response",response.toString());
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject schObj= response.getJSONObject(i);
                        ScheduleModel sch=new ScheduleModel();
//                        talk.setName("Aysuh KAusnldjhlkhfkllnewlfnlwenflkjewlkjfljwhekjksdjkjhkuhkjhkjsdhlehlkjhalhldhll");
//                        talk.setVenue("LEcture aHAljewnfkljcnkjhfewkkjhefkjwhkfjwkejfhkwehkfhkwejnfkll");
                        //talk.setRegURL("https://github.com/appteam-nith/nimbus2019");
//                        talk.setInfo("HE is veryhlhfeldijvoikbfewkjbkfjwkejfkjwejeovijoeijvoeijdvoijeoijeovjioejioeijvovjoeidjvlkdsnlkvn jsndoviejoiejvoljkdlkjvoeijvoiejovijdokjdeoivjolj");
//                        talk.setDate("19 2022002345453453453450 2");
                        sch.setNameSch( schObj.getString("name"));
                        sch.setTimeSch("Date : " + schObj.getString("time"));
                        //sch.setS(talkObj.getString("info"));
                       // sch.setRegURL(  talkObj.getString("regUrl"));
                        sch.setVenueSch("Venue : " + schObj.getString("venue"));

                        // Log.d("Talk",talk.getName());



                        scheduleModelList.add(sch);
                        scheduleRecyclerViewAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage());

            }
        });
        requestQueueSch.add(jsonArrayRequest);


        return scheduleModelList;
    }

}
