package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Add_Talk extends AppCompatActivity {
    private EditText nameAdd,infoAdd,venueAdd,dateAdd,imageAdd,regUrlAdd;
    private Button addButton;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__talk);
        nameAdd=findViewById(R.id.spkNameAdd);
        infoAdd=findViewById(R.id.infoAdd);
        venueAdd=findViewById(R.id.spkrvenueAdd);
        dateAdd=findViewById(R.id.dateAdd);
        imageAdd=findViewById(R.id.imageAdd);
        regUrlAdd=findViewById(R.id.addregUrl);
        addButton=findViewById(R.id.AddButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data="{"+"name"+ nameAdd.getText().toString()+","+"info"+ infoAdd.getText().toString()+","+"venue"+venueAdd.getText().toString()
//                        +","+"date"+dateAdd.getText().toString()+","+"image"+imageAdd.getText().toString()+","+"regUrl"+regUrlAdd.getText().toString()+"}";


                AddDetails();
            }
        });


    }

    private void AddDetails() {
        //final String savedata=data;
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request= new StringRequest(Request.Method.POST, Constant.Url+ "talks" ,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    Log.i("Tag","Success");
                    Toast.makeText(getApplicationContext(),object.toString(),Toast.LENGTH_SHORT).show();



                }catch (JSONException e){
                    Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_SHORT).show();


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley","Error: "+ error.getMessage());
                error.printStackTrace();
                Toast.makeText(getApplication(),"Error:"+error,Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
                    public String getBodyContentType(){
                    return "application/x-www-form-urlencoded; charset=utf-8";
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", nameAdd.getText().toString());
                params.put("info",infoAdd.getText().toString());
                params.put("venue",venueAdd.getText().toString());
                params.put("date",dateAdd.getText().toString());
                params.put("image",imageAdd.getText().toString());
                params.put("regUrl",regUrlAdd.getText().toString());
                return params;
            }
        };

        requestQueue.add(request);
    }
}
