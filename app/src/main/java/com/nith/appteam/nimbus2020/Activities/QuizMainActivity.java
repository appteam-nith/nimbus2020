package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.nith.appteam.nimbus2020.Adapters.QuizRecyclerAdapter;
import com.nith.appteam.nimbus2020.Models.Id_Value;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class QuizMainActivity extends AppCompatActivity {
    RecyclerView quizrecyclerView;
    RequestQueue queue;
    ArrayList<Id_Value> quiztypes =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_main);
        quizrecyclerView=findViewById(R.id.quizrecyclerview);
        queue = Volley.newRequestQueue(QuizMainActivity.this);

        quizrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        quizrecyclerView.setAdapter(new QuizRecyclerAdapter(QuizMainActivity.this,quiztypes));
        getdata();
        quizrecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, quizrecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                    postdata(position);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }



    private  void getdata(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,getString(R.string.baseUrl)+"/quiz/departments", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        Id_Value idValue =new Id_Value(jsonArray.getJSONObject(i).getString("departmentName"),
                                jsonArray.getJSONObject(i).getString("departmentId"));
                        quiztypes.add(idValue);
                        Objects.requireNonNull(quizrecyclerView.getAdapter()).notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Loggerreer",error.toString());

            }
        });


        queue.add(stringRequest);


    }





    private void postdata(final int position){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,getString(R.string.baseUrl)+"/quiz/departments", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent i=new Intent(QuizMainActivity.this,DepartmentQuiz.class);
                i.putExtra("quiz",response);
                i.putExtra("departmentname",quiztypes.get(position).getValue());
                startActivity(i);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }){

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("deptId",quiztypes.get(position).getId());
                return params;
            }

        };

        queue.add(stringRequest);

    }




}
