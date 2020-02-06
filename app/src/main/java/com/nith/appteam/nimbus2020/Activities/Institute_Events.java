package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Institute_Events extends AppCompatActivity {
    private RecyclerView recyclerViewIEVE;
    private EventIRecyclerViewAdapter eventIRecyclerViewAdapter;
    private RequestQueue requestQueueEVEI;
    private List<instituteEvent> eventIlist;
    private ProgressBar loadWall;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private EditText num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_);
        FloatingActionButton fab = findViewById(R.id.fabI);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();

            }
        });
        requestQueueEVEI = Volley.newRequestQueue(this);
loadWall=findViewById(R.id.loadwallEventI);
        recyclerViewIEVE = findViewById(R.id.recyclerViewEVEI);
        recyclerViewIEVE.setHasFixedSize(true);
        recyclerViewIEVE.setLayoutManager(new LinearLayoutManager(this));
        eventIlist = new ArrayList<>();
        PrefsIevent prefsIevent = new PrefsIevent(this);
        String search = prefsIevent.getSearch();
        eventIlist = getEventI(search);
        //   talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
        // recyclerView.setAdapter(talkRecyclerViewAdapter);
        //     talkRecyclerViewAdapter.notifyDataSetChanged();

    }

    public List<instituteEvent> getEventI(String searchTerm)//all info returned from api
    {
        loadWall.setVisibility(View.VISIBLE);
        eventIlist.clear();
        eventIRecyclerViewAdapter = new EventIRecyclerViewAdapter(this, eventIlist);
        recyclerViewIEVE.setAdapter(eventIRecyclerViewAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Constant.Url + searchTerm, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loadWall.setVisibility(View.GONE);
                Log.d("Response", response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject talkObj = response.getJSONObject(i);
                        instituteEvent Ievent = new instituteEvent();
//                        talk.setName("Aysuh
//                        KAusnldjhlkhfkllnewlfnlwenflkjewlkjfljwhekjksdjkjhkuhkjhkjsdhlehlkjhalhldhll");
//                        talk.setVenue("LEcture
//                        aHAljewnfkljcnkjhfewkkjhefkjwhkfjwkejfhkwehkfhkwejnfkll");
                        //    Ievent.setRegURL("https://github.com/appteam-nith/nimbus2019");
//                        talk.setInfo("HE is
//                        veryhlhfeldijvoikbfewkjbkfjwkejfkjwejeovijoeijvoeijdvoijeoijeovjioejioeijvovjoeidjvlkdsnlkvn jsndoviejoiejvoljkdlkjvoeijvoiejovijdokjdeoivjolj");
//                        talk.setDate("19 2022002345453453453450 2");
                        Ievent.setNameIEVE(talkObj.getString("name"));
                        Ievent.setDateIEVE("On: " + talkObj.getString("date"));
                        Ievent.setImageIEVE(talkObj.getString("image"));
                        Ievent.setInfoIEVE(talkObj.getString("info"));
                        Ievent.setRegURLIEVE(talkObj.getString("regUrl"));
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
                Log.d("Error", error.getMessage());

            }
        });
        requestQueueEVEI.add(jsonArrayRequest);


        return eventIlist;
    }

    public void showInputDialog()
    {
        alertDialogBuilder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.dialog_view,null);
        num= view.findViewById(R.id.dialog_edit);
        Button submit= view.findViewById(R.id.submitButton);
        alertDialogBuilder.setView(view);
        dialog=alertDialogBuilder.create();
        dialog.show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num.getText().toString().equals("8219341697")) {
                    Intent intent = new Intent(Institute_Events.this, Add_I_Events.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Institute_Events.this,"Not Allowed",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }
}


