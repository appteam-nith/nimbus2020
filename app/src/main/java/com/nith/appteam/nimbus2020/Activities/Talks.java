package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nith.appteam.nimbus2020.Adapters.TalkRecyclerViewAdapter;
import com.nith.appteam.nimbus2020.Models.TalkModel;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;
import com.nith.appteam.nimbus2020.Utils.PrefsTalk;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Talks extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TalkModel> talkList;
    private TalkRecyclerViewAdapter talkRecyclerViewAdapter;
    private RequestQueue requestQueue;
    ProgressBar loadwall;
    private ImageView talkk;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private EditText num;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talks);
        requestQueue = Volley.newRequestQueue(this);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbartalk);
        Toolbar collapsingToolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);
        setSupportActionBar(collapsingToolbar);
        talkk=findViewById(R.id.talkImageView);
        Picasso.with(this).load(R.drawable.talk).fit().into(talkk);

        FloatingActionButton fab = findViewById(R.id.fabtalk);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();

            }
        });
        loadwall = findViewById(R.id.loadwallTalk);
        recyclerView = findViewById(R.id.recyclerViewTalk);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        talkList = new ArrayList<>();
        PrefsTalk prefsTalk = new PrefsTalk(this);
        String search = prefsTalk.getSearch();
        talkList = getTalk(search);
        //   talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
        // recyclerView.setAdapter(talkRecyclerViewAdapter);
        //     talkRecyclerViewAdapter.notifyDataSetChanged();

    }

    public List<TalkModel> getTalk(String searchTerm)//all info returned from api
    {
        loadwall.setVisibility(View.VISIBLE);
        talkList.clear();
        talkRecyclerViewAdapter = new TalkRecyclerViewAdapter(this, talkList);
        recyclerView.setAdapter(talkRecyclerViewAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Constant.Url + searchTerm, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loadwall.setVisibility(View.GONE);
                Log.d("Response", response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject talkObj= response.getJSONObject(i);
                        TalkModel talk=new TalkModel();
//                        talk.setName("Aysuh KAusnldjhlkhfkllnewlfnlwenflkjewlkjfljwhekjksdjkjhkuhkjhkjsdhlehlkjhalhldhll");
//                        talk.setVenue("LEcture aHAljewnfkljcnkjhfewkkjhefkjwhkfjwkejfhkwehkfhkwejnfkll");
                       //talk.setRegURL("https://github.com/appteam-nith/nimbus2019");
//                        talk.setInfo("HE is veryhlhfeldijvoikbfewkjbkfjwkejfkjwejeovijoeijvoeijdvoijeoijeovjioejioeijvovjoeidjvlkdsnlkvn jsndoviejoiejvoljkdlkjvoeijvoiejovijdokjdeoivjolj");
//                        talk.setDate("19 2022002345453453453450 2");
                        talk.setName(talkObj.getString("name"));
                        talk.setDate("On: " + talkObj.getString("date"));
                        talk.setImage(talkObj.getString("image"));
                        talk.setIdTalk(talkObj.getString("_id"));
                        talk.setInfo(talkObj.getString("info"));
                      talk.setRegURL(  talkObj.getString("regUrl"));
                        talk.setVenue("Venue: " + talkObj.getString("venue"));
                        // Log.d("Talk",talk.getName());
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
                Log.d("Error", error.getMessage());

            }
        });
        requestQueue.add(jsonArrayRequest);


        return talkList;
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
                        Intent intent = new Intent(Talks.this, Add_Talk.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Talks.this,"Not Allowed",Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
        }
    }


