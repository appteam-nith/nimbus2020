package com.nith.appteam.nimbus2020.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nith.appteam.nimbus2020.Adapters.ScheduleRecyclerAdaptor;
import com.nith.appteam.nimbus2020.Models.Schedule;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.IResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Day3Fragment extends Fragment {
    ArrayList<Schedule> arrayList;
    Context context;
    ScheduleRecyclerAdaptor adaptor;
    private IResult mResultCallback;
    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragmentday3,container,false);

        arrayList = new ArrayList<Schedule>();
        requestQueue= Volley.newRequestQueue(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview3);
        getdata();
        adaptor = new ScheduleRecyclerAdaptor(context, arrayList);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }
    private  void  getdata(){
        StringRequest s=new StringRequest(Request.Method.GET, "http://api.festnimbus.com/schedule", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", "onResponse: "+response);

                try {

                    JSONArray jsonArray= new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Schedule schedule= new Schedule();
                        schedule.setTime(obj.getString("time"));
                        schedule.setName(obj.getString("name"));
                        schedule.setInfo(obj.getString("info"));
                        schedule.setDepartment(obj.getString("departmentName"));
                        schedule.setVenue(obj.getString("venue"));
                        arrayList.add(schedule);
                    }
                    adaptor.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","error");
            }
        });

        requestQueue.add(s);


    }

}

