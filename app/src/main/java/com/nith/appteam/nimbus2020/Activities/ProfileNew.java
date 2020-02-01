package com.nith.appteam.nimbus2020.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.policy.TimeWindow;
import com.nith.appteam.nimbus2020.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileNew extends AppCompatActivity {

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;
    private EditText name, rollno, phoneNumber, college;
    private Button submitProfile;
    private ProgressBar progressBar;
    private CircleImageView profilePic;
    private int PICK_PHOTO_CODE = 100;
    private byte[] byteArray;
    private String imageUrl = "";
    private Bitmap bmp, img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_new);
        sharedPrefs = getSharedPreferences("app", MODE_PRIVATE);
        editor = sharedPrefs.edit();
        getUI();

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }
            }
        });
        submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUrl.equals(""))
                    imageUrl = String.valueOf(R.string.defaultImageUrl);
                if (!name.getText().toString().isEmpty() && !rollno.getText().toString().isEmpty() &&
                        !phoneNumber.getText().toString().isEmpty() && !college.getText().toString().isEmpty()) {

                    progressBar.setVisibility(View.VISIBLE);
                    JSONObject details = new JSONObject();
                    try {
                        details.put("name", name.getText().toString());
                        details.put("rollNumber", rollno.getText().toString());
                        details.put("college", college.getText().toString());
                        //details.put("campusAmbassador", false);
                        details.put("image", imageUrl);
                        details.put("phone", phoneNumber);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.v("TESTING", String.valueOf(details));
                    final String requestBody = details.toString();
                    RequestQueue queue = Volley.newRequestQueue(ProfileNew.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, getString(R.string.baseUrl) + "/auth/signup", new com.android.volley.Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(ProfileNew.this, response, Toast.LENGTH_LONG).show();
                            if (Integer.parseInt(response) == 0) {
                                editor.putString("name", name.getText().toString());
                                editor.putString("rollno", rollno.getText().toString());
                                editor.putString("college", college.getText().toString());
                                editor.putString("phone", phoneNumber.getText().toString());
                                editor.putString("image", imageUrl);
                                editor.putBoolean("profileStatus", true);
                                editor.commit();
                                progressBar.setVisibility(View.GONE);
                                Intent i = new Intent(ProfileNew.this, MainActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                            }
                        }
                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY", error.toString());
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ProfileNew.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public Map<String, String> getHeaders() {
                            HashMap<String, String> headers = new HashMap<>();
                            headers.put("token", sharedPrefs.getString("firebaseId", ""));
                            return headers;
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {

                                return requestBody == null ? null : requestBody.getBytes("utf-8");


                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                return null;
                            }
                        }
                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    queue.add(stringRequest);
                } else
                    Toast.makeText(ProfileNew.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
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
            profilePic.setImageBitmap(img);
            Bitmap bitmap = ((BitmapDrawable) profilePic.getDrawable()).getBitmap();
            progressBar.setVisibility(View.VISIBLE);
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
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {
                        Log.i("HELLO", "JIJIJ");
//                      finish();
                        Toast.makeText(ProfileNew.this, "Upload Failed" + error.getDescription() + " requestId" + requestId, Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {
                        // your code here
                    }
                })
                .dispatch(ProfileNew.this);

    }

    private void getUI() {
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.mobile);
        rollno = findViewById(R.id.mobile);
        college = findViewById(R.id.college);
        submitProfile = findViewById(R.id.submit_profile);
        profilePic = findViewById(R.id.profile_pic);
        progressBar = findViewById(R.id.profile_progress);
    }
}
