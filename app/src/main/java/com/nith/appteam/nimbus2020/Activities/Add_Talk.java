package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.policy.TimeWindow;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Add_Talk extends AppCompatActivity {
    private EditText nameAdd,infoAdd,venueAdd,dateAdd,regUrlAdd;
    private CircleImageView imageAddTalk;
    private Button addButton;
    private RequestQueue requestQueue;
    private int PICK_PHOTO_CODE = 100;
    private byte[] byteArray;
    private String imageUrl = "";
    private Bitmap bmp, img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__talk);
        nameAdd=findViewById(R.id.NameAddTalk);
        infoAdd=findViewById(R.id.infoAddTalk);
        venueAdd=findViewById(R.id.venueAddTalk);
        dateAdd=findViewById(R.id.dateAddTalk);
        imageAddTalk=findViewById(R.id.addImgTalk);
        regUrlAdd=findViewById(R.id.addregUrlTalk);
        addButton=findViewById(R.id.AddButtonTalk);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data="{"+"name"+ nameAdd.getText().toString()+","+"info"+ infoAdd.getText().toString()+","+"venue"+venueAdd.getText().toString()
//                        +","+"date"+dateAdd.getText().toString()+","+"image"+imageAdd.getText().toString()+","+"regUrl"+regUrlAdd.getText().toString()+"}";


                AddDetails();
            }
        });


        imageAddTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri photoUri = data.getData();
            Bitmap selectedImage = null;
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, bs);
            byteArray = bs.toByteArray();
            bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            img = getResizedBitmap(bmp, 300);
//          pass = encodeTobase64(img);
            imageAddTalk.setImageBitmap(img);
            Bitmap bitmap = ((BitmapDrawable) imageAddTalk.getDrawable()).getBitmap();

            getImageUrl(bitmap);
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void getImageUrl(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byteArray = stream.toByteArray();
        String requestId = MediaManager.get().upload(byteArray).constrain(TimeWindow.immediate())
                .unsigned("x2gjlxpr")
                .option("connect_timeout", 10000)
                .option("read_timeout", 10000)
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {
                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {
                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        imageUrl = String.valueOf(resultData.get("url"));

                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.i("HELLO", "JIJIJ");
//                      finish();
                        Toast.makeText(Add_Talk.this, "Upload Failed" + error.getDescription() + " requestId" + requestId, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        // your code here
                    }
                })
                .dispatch(Add_Talk.this);

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
                    if(object.getString("message").equals("success") ){

                        nameAdd.setText("");
                        regUrlAdd.setText("");
                        venueAdd.setText("");
                        dateAdd.setText("");

                        infoAdd.setText("");

                    }



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
                params.put("image",imageUrl);
    //            params.put("image",imageAdd.getText().toString());
                params.put("regUrl",regUrlAdd.getText().toString());
                return params;
            }
        };

        requestQueue.add(request);
    }
}
