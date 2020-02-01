package com.nith.appteam.nimbus2020.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import androidx.appcompat.app.AppCompatActivity;

public class Add_Workshop extends AppCompatActivity {

    private EditText nameAddWrk, infoAddWrk, venueAddWrk, dateAddWrk, imageAddWrk, regUrlAddWrk,
            typeAddWrk;
    private Button addButtonWork;
    private RequestQueue requestQueueWrk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__workshop);
        nameAddWrk = findViewById(R.id.wrkNameAdd);
        typeAddWrk = findViewById(R.id.addTypeWrk);
        infoAddWrk = findViewById(R.id.infoAddWrk);
        venueAddWrk = findViewById(R.id.wrkvenueAdd);
        dateAddWrk = findViewById(R.id.dateAddWrk);
        imageAddWrk = findViewById(R.id.imageAddWrk);
        regUrlAddWrk = findViewById(R.id.addregUrlWrk);
        addButtonWork = (Button) findViewById(R.id.AddButtonWrk);

        addButtonWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data="{"+"name"+ nameAdd.getText().toString()+","+"info"+ infoAdd
//                .getText().toString()+","+"venue"+venueAdd.getText().toString()
//                        +","+"date"+dateAdd.getText().toString()+","+"image"+imageAdd.getText()
//                        .toString()+","+"regUrl"+regUrlAdd.getText().toString()+"}";


                AddDetailsWrk();
            }
        });


    }

    private void AddDetailsWrk() {
        //final String savedata=data;
        requestQueueWrk = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Constant.Url + "workshops",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Log.i("Tag", "Success");
                            Toast.makeText(getApplicationContext(), object.toString(),
                                    Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Error" + e,
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(getApplication(), "Error:" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=utf-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", nameAddWrk.getText().toString());
                params.put("info", infoAddWrk.getText().toString());
                params.put("venue", venueAddWrk.getText().toString());
                params.put("date", dateAddWrk.getText().toString());
                params.put("image", imageAddWrk.getText().toString());
                params.put("regUrl", regUrlAddWrk.getText().toString());
                params.put("type", typeAddWrk.getText().toString());
                return params;
            }
        };

        requestQueueWrk.add(request);
    }
}