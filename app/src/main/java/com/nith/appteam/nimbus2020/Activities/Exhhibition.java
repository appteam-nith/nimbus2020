package com.nith.appteam.nimbus2020.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nith.appteam.nimbus2020.Adapters.ExhibitionRecyclerViewAdapter;
import com.nith.appteam.nimbus2020.Models.ExhibitionModel;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;
import com.nith.appteam.nimbus2020.Utils.PrefsExhibition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Exhhibition extends AppCompatActivity {

    private RecyclerView recyclerViewExhib;
    private List<ExhibitionModel> exhibitionList;
    private ExhibitionRecyclerViewAdapter exhibitionRecyclerViewAdapter;
    private RequestQueue requestQueueExh;
    private ProgressBar loadWall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhhibition);
        requestQueueExh = Volley.newRequestQueue(this);
        Toolbar toolbar = findViewById(R.id.toolbarExh);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabExh);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exhhibition.this, Add_Exhibition.class);
                startActivity(intent);


            }
        });
        loadWall=findViewById(R.id.loadwallExh);
        recyclerViewExhib = findViewById(R.id.recyclerViewExhibition);
        recyclerViewExhib.setHasFixedSize(true);
        recyclerViewExhib.setLayoutManager(new LinearLayoutManager(this));
        exhibitionList = new ArrayList<>();
        PrefsExhibition prefsExhibition = new PrefsExhibition(this);
        String search = prefsExhibition.getSearch();
        exhibitionList = getExhibition(search);
//        talkRecyclerViewAdapter=new TalkRecyclerViewAdapter(this,talkList);
//        recyclerView.setAdapter(talkRecyclerViewAdapter);

    }

    public List<ExhibitionModel> getExhibition(String searchTerm)//all info returned from api
    {
        loadWall.setVisibility(View.VISIBLE);
        exhibitionList.clear();
        exhibitionRecyclerViewAdapter = new ExhibitionRecyclerViewAdapter(this, exhibitionList);
        recyclerViewExhib.setAdapter(exhibitionRecyclerViewAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Constant.Url + searchTerm, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loadWall.setVisibility(View.GONE);
//                Log.d("Response",response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject exhObj = response.getJSONObject(i);
                        ExhibitionModel exhibition = new ExhibitionModel();
//                        talk.setName("APP on TALkoinghg iguhedbfuhcgwu");
//                        talk.setVenue("LEcture aHAljewnfkljcnkjhfewkkjhefkjwhkfjwkejfhkwehkfhkwejnfkll");
                        exhibition.setRegURLExh("https://google.com");
//                        talk.setInfo("HE is veryhlhfeldijvoikbfewkjbkfjwkejfkjwejeovijoeijvoeijdvoijeoijeovjioejioeijvovjoeidjvlkdsnlkvn jsndoviejoiejvoljkdlkjvoeijvoiejovijdokjdeoivjolj");
//                        talk.setDate("19 2022002345453453453450 2");
                        exhibition.setNameExh(exhObj.getString("name"));
                        exhibition.setDateExh("On: " + exhObj.getString("date"));
                        exhibition.setImageExh(exhObj.getString("image"));
                        //exhibition.setImageExh("https://www.google
                        // .com/search?q=images&rlz=1C1CHBF_enIN859IN859&sxsrf
                        // =ACYBGNS3W0FihD42Fyr2cRgnJ33k2Ihysw:1580381365046&source=lnms&tbm=isch
                        // &sa=X&ved=2ahUKEwj94_2uk6vnAhU1zTgGHUz7DEAQ_AUoAXoECA4QAw&biw=1536&bih
                        // =754#imgrc=_2JirDBiGzi3lM:");
                        exhibition.setInfoExh(exhObj.getString("info"));
                        //exhibition.setRegURLExh(  exhObj.getString("regUrl"));
                        exhibition.setVenueExh("Venue: " + exhObj.getString("venue"));
//                         Log.d("Talk",talk.getName());
//                       Log.d("date",talk.getDate());
                        exhibitionList.add(exhibition);
                        exhibitionRecyclerViewAdapter.notifyDataSetChanged();

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
        requestQueueExh.add(jsonArrayRequest);


        return exhibitionList;
    }
    private void runLayoutAnim(final RecyclerView recyclerView)
    {
        final Context context= recyclerView.getContext();
        final LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }


}