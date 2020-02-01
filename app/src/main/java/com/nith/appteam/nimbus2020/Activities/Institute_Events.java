
package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.nith.appteam.nimbus2020.Adapters.EventIRecyclerViewAdapter;
import com.nith.appteam.nimbus2020.Models.instituteEvent;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;
import com.nith.appteam.nimbus2020.Utils.PrefsIevent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Institute_Events extends AppCompatActivity {
    private RecyclerView recyclerViewIEVE;
    private EventIRecyclerViewAdapter eventIRecyclerViewAdapter;
    private RequestQueue requestQueueEVEI;
    private List<instituteEvent> eventIlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_);
        requestQueueEVEI= Volley.newRequestQueue(this);

        recyclerViewIEVE= findViewById(R.id.recyclerViewEVEI);
        recyclerViewIEVE.setHasFixedSize(true);
        recyclerViewIEVE.setLayoutManager( new LinearLayoutManager(this));
        eventIlist= new ArrayList<>();
        PrefsIevent prefsIevent=new PrefsIevent(this);
        String search=prefsIevent.getSearch();
        eventIlist=getEventI(search);
        //   talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
        // recyclerView.setAdapter(talkRecyclerViewAdapter);
        //     talkRecyclerViewAdapter.notifyDataSetChanged();

    }
    public List<instituteEvent> getEventI(String searchTerm)//all info returned from api
    {
        eventIlist.clear();
        eventIRecyclerViewAdapter=new EventIRecyclerViewAdapter(this,eventIlist);
        recyclerViewIEVE.setAdapter(eventIRecyclerViewAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constant.Url+ searchTerm,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Response",response.toString());
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject talkObj= response.getJSONObject(i);
                        instituteEvent Ievent=new instituteEvent();
//                        talk.setName("Aysuh KAusnldjhlkhfkllnewlfnlwenflkjewlkjfljwhekjksdjkjhkuhkjhkjsdhlehlkjhalhldhll");
//                        talk.setVenue("LEcture aHAljewnfkljcnkjhfewkkjhefkjwhkfjwkejfhkwehkfhkwejnfkll");
                    //    Ievent.setRegURL("https://github.com/appteam-nith/nimbus2019");
//                        talk.setInfo("HE is veryhlhfeldijvoikbfewkjbkfjwkejfkjwejeovijoeijvoeijdvoijeoijeovjioejioeijvovjoeidjvlkdsnlkvn jsndoviejoiejvoljkdlkjvoeijvoiejovijdokjdeoivjolj");
//                        talk.setDate("19 2022002345453453453450 2");
                        Ievent.setNameIEVE(talkObj.getString("name"));
                        Ievent.setDateIEVE("On: " + talkObj.getString("date"));
                        Ievent.setImageIEVE(talkObj.getString("image"));
                        Ievent.setInfoIEVE(talkObj.getString("info"));
                        Ievent.setRegURLIEVE(  talkObj.getString("regUrl"));
                        Ievent.setVenueIEVE("Venue: " + talkObj.getString("venue"));
                        // Log.d("Talk",talk.getName());
                        //Log.d("date",talk.getDate());
                        eventIlist.add(Ievent);
                        eventIRecyclerViewAdapter.notifyDataSetChanged();

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
        requestQueueEVEI.add(jsonArrayRequest);


        return eventIlist;
    }

}


