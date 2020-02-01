package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nith.appteam.nimbus2020.Adapters.TalkRecyclerViewAdapter;
import com.nith.appteam.nimbus2020.Models.TalkModel;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;
import com.nith.appteam.nimbus2020.Utils.PrefsTalk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Talks extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TalkModel> talkList;
    private TalkRecyclerViewAdapter talkRecyclerViewAdapter;
    private RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talks);
        requestQueue= Volley.newRequestQueue(this);
        Toolbar toolbar = findViewById(R.id.toolbartalk);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabtalk);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Talks.this,Add_Talk.class);
                startActivity(intent);

            }
        });
        recyclerView= findViewById(R.id.recyclerViewTalk);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        talkList= new ArrayList<>();
        PrefsTalk prefsTalk=new PrefsTalk(this);
        String search=prefsTalk.getSearch();
        talkList=getTalk(search);
     //   talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
       // recyclerView.setAdapter(talkRecyclerViewAdapter);
   //     talkRecyclerViewAdapter.notifyDataSetChanged();

    }
    public List<TalkModel> getTalk(String searchTerm)//all info returned from api
    {
        talkList.clear();
        talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
        recyclerView.setAdapter(talkRecyclerViewAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constant.Url+ searchTerm,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
          Log.d("Response",response.toString());
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject talkObj= response.getJSONObject(i);
                        TalkModel talk=new TalkModel();
//                        talk.setName("Aysuh KAusnldjhlkhfkllnewlfnlwenflkjewlkjfljwhekjksdjkjhkuhkjhkjsdhlehlkjhalhldhll");
//                        talk.setVenue("LEcture aHAljewnfkljcnkjhfewkkjhefkjwhkfjwkejfhkwehkfhkwejnfkll");
                       talk.setRegURL("https://github.com/appteam-nith/nimbus2019");
//                        talk.setInfo("HE is veryhlhfeldijvoikbfewkjbkfjwkejfkjwejeovijoeijvoeijdvoijeoijeovjioejioeijvovjoeidjvlkdsnlkvn jsndoviejoiejvoljkdlkjvoeijvoiejovijdokjdeoivjolj");
//                        talk.setDate("19 2022002345453453453450 2");
                        talk.setName(talkObj.getString("name"));
                        talk.setDate("On: " + talkObj.getString("date"));
                        talk.setImage(talkObj.getString("image"));
                        talk.setInfo(talkObj.getString("info"));
                       // talk.setRegURL(  talkObj.getString("regUrl"));
                        talk.setVenue("Venue: " + talkObj.getString("venue"));
                        // Log.d("Talk",talk.getName());
                       //Log.d("date",talk.getDate());
                      talkList.add(talk);
                        talkRecyclerViewAdapter.notifyDataSetChanged();

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
        requestQueue.add(jsonArrayRequest);


        return talkList;
    }

}
