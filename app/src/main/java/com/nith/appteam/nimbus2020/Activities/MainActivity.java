package com.nith.appteam.nimbus2020.Activities;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.nith.appteam.nimbus2020.Adapters.DiscoverAdapter;
import com.nith.appteam.nimbus2020.Models.DiscoverModel;
import com.nith.appteam.nimbus2020.R;
import com.nith.appteam.nimbus2020.Services.GeofenceRegistrationService;
import com.nith.appteam.nimbus2020.Utils.Constant;
import com.nith.appteam.nimbus2020.Utils.IResult;
import com.nith.appteam.nimbus2020.Utils.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_LOCATION_PERMISSION_CODE = 101;
    RecyclerView mRecyclerView;
    DiscoverAdapter mDiscoverAdapter;
    List<DiscoverModel> mDiscoverModelList;
    private IResult mResultCallback;
    private Button quiz, sponsor, profile, campusA, workshops, talks, events, qr, exhibition,
            schedule, contributors, coreTeam;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private GeofencingRequest geofencingRequest;
    private GoogleApiClient googleApiClient;
    private boolean isMonitoring = false;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_LOCATION_PERMISSION_CODE);
        }

        getUI();
//
//        profileButton = findViewById(R.id.profile_button);
//        post = findViewById(R.id.post);
//        Picasso.with(MainActivity.this)
//                .load(sharedPref.getString("imageUrl", String.valueOf(R.string.defaultImageUrl)))
//                .into(profileButton);
        sharedPref = getSharedPreferences("app", MODE_PRIVATE);
        editor = sharedPref.edit();

        // Checking whether user has logged in or not
        if (sharedPref.getBoolean("loginStatus", false) == false) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        //Checking whether user has created profile or not
        else if (sharedPref.getBoolean("profileStatus", false) == false) {
            Intent i = new Intent(this, ProfileNew.class);
            startActivity(i);
            finish();
        }

        coreTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CoreTeamActivity.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileMain.class);
                startActivity(i);
            }
        });
        campusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CampusAmbassador.class);
                startActivity(i);
            }
        });


        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizMainActivity.class);
                startActivity(intent);
            }
        });


        sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SponsorsActivity.class);
                startActivity(intent);
            }
        });

        workshops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Workshops.class);
                startActivity(intent);
            }
        });
        talks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Talks.class);
                startActivity(intent);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Event_Choose.class);
                startActivity(intent);
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Schedule_Day.class);
                startActivity(intent);
            }
        });
        exhibition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Exhhibition.class);
                startActivity(intent);
            }
        });


        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRScanner.class);
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mDiscoverModelList = new ArrayList<>();

        getData();


        mDiscoverAdapter = new DiscoverAdapter(mDiscoverModelList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mDiscoverAdapter);
        mDiscoverAdapter.notifyDataSetChanged();
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);

    }


    private void getData() {
        mDiscoverModelList.clear();
        //  loadwall.setVisibility(View.VISIBLE);

        initVolleyCallback();

        final VolleyService mVolleyService = new VolleyService(mResultCallback, this);

        mVolleyService.getJsonArrayDataVolley("GETDISCOVER",
                getString(R.string.baseUrl) + "/discover");

    }


    void initVolleyCallback() {
        mResultCallback = new IResult() {
            JSONObject obj;

            @Override
            public void notifySuccess(String requestType, JSONObject response,
                    JSONArray jsonArray) {


                if (response != null) {
                    //loadwall.setVisibility(View.GONE);

                    try {
                        obj = response;
                        String name = obj.getString("name");
                        String time = obj.getString("time");
                        String location = obj.getString("location");
                        String image = obj.getString("image_src");
                        mDiscoverModelList.add(new DiscoverModel(name, location, time, image));
                        mDiscoverAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Log.e("Hellcatt", response.toString());

                } else {
                    Log.e("zHell", jsonArray.toString());

                    //  loadwall.setVisibility(View.GONE);


                    for (int i = 0; i < jsonArray.length(); i++) {

                        try {
                            obj = jsonArray.getJSONObject(i);
                            String name = obj.getString("name");
                            String time = obj.getString("time");
                            String location = obj.getString("location");
                            String image = obj.getString("image_src");
                            mDiscoverModelList.add(new DiscoverModel(name, location, time, image));
                            mDiscoverAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    mDiscoverAdapter.notifyDataSetChanged();

                }
            }


            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.i("error", error.toString());
            }
        };

    }


    private void getUI() {
        quiz = findViewById(R.id.quiz);
        sponsor = findViewById(R.id.sponsors);
        talks = findViewById(R.id.talks);
        workshops = findViewById(R.id.workshops);
        events = findViewById(R.id.events);
        campusA = findViewById(R.id.ca);
        profile = findViewById(R.id.profile);
        qr = findViewById(R.id.qr);
        exhibition = findViewById(R.id.exhibition);
        schedule = findViewById(R.id.schedule);
        coreTeam = findViewById(R.id.coreTeam);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private void startLocationMonitor() {
        Log.d(TAG, "start location monitor");
        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(2000)
                .setFastestInterval(1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                    locationRequest, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {

//                    if (currentLocationMarker != null) {
//                        currentLocationMarker.remove();
//                    }
//                    markerOptions = new MarkerOptions();
//                    markerOptions.position(new LatLng(location.getLatitude(), location
//                    .getLongitude()));
//                    markerOptions.title("Current Location");
//                    currentLocationMarker = googleMap.addMarker(markerOptions);
                            Log.d(TAG, "Location Change Lat Lng " + location.getLatitude() + " "
                                    + location.getLongitude());
                        }
                    });
        } catch (SecurityException e) {
            Log.d(TAG, e.getMessage());
        }

    }

    private void startGeofencing() {
        Log.d(TAG, "Start geofencing monitoring call");
        pendingIntent = getGeofencePendingIntent();
        geofencingRequest = new GeofencingRequest.Builder()
                .setInitialTrigger(Geofence.GEOFENCE_TRANSITION_ENTER)
                .addGeofence(getGeofence())
                .build();

        if (!googleApiClient.isConnected()) {
            Log.d(TAG, "Google API client not connected");
        } else {
            try {
                LocationServices.GeofencingApi.addGeofences(googleApiClient, geofencingRequest,
                        pendingIntent).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            Log.d(TAG, "Successfully Geofencing Connected");
                        } else {
                            Log.d(TAG, "Failed to add Geofencing " + status.getStatus());
                        }
                    }
                });
            } catch (SecurityException e) {
                Log.d(TAG, e.getMessage());
            }
        }
        isMonitoring = true;
        invalidateOptionsMenu();
    }

    @NonNull
    private Geofence getGeofence() {
        LatLng latLng = Constant.AREA_LANDMARKS.get(Constant.GEOFENCE_ID);
        return new Geofence.Builder()
                .setRequestId(Constant.GEOFENCE_ID)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setCircularRegion(latLng.latitude, latLng.longitude,
                        Constant.GEOFENCE_RADIUS_IN_METERS)
                .setNotificationResponsiveness(1000)
                .setTransitionTypes(
                        Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }

    private PendingIntent getGeofencePendingIntent() {
        if (pendingIntent != null) {
            return pendingIntent;
        }
        Intent intent = new Intent(this, GeofenceRegistrationService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
    }

    private void stopGeoFencing() {
        pendingIntent = getGeofencePendingIntent();
        LocationServices.GeofencingApi.removeGeofences(googleApiClient, pendingIntent)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            Log.d(TAG, "Stop geofencing");
                        } else {
                            Log.d(TAG, "Not stop geofencing");
                        }
                    }
                });
        isMonitoring = false;
        invalidateOptionsMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                MainActivity.this);
        if (response != ConnectionResult.SUCCESS) {
            Log.d(TAG, "Google Play Service Not Available");
            GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, response,
                    1).show();
        } else {
            Log.d(TAG, "Google play service available");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.reconnect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_map_activity, menu);
        if (isMonitoring) {
            menu.findItem(R.id.action_start_monitor).setVisible(false);
            menu.findItem(R.id.action_stop_monitor).setVisible(true);
        } else {
            menu.findItem(R.id.action_start_monitor).setVisible(true);
            menu.findItem(R.id.action_stop_monitor).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_start_monitor:
                startGeofencing();
                break;
            case R.id.action_stop_monitor:
                stopGeoFencing();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
//        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//
//       // this.googleMap = googleMap;
//        LatLng latLng = Constants.AREA_LANDMARKS.get(Constants.GEOFENCE_ID);
//        googleMap.addMarker(new MarkerOptions().position(latLng).title("TACME"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f));
//
//        googleMap.setMyLocationEnabled(true);
//
////        Circle circle = googleMap.addCircle(new CircleOptions()
////                .center(new LatLng(latLng.latitude, latLng.longitude))
////                .radius(Constants.GEOFENCE_RADIUS_IN_METERS)
////                .strokeColor(Color.RED)
////                .strokeWidth(4f));
//
//    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Google Api Client Connected");
        isMonitoring = true;
        startGeofencing();
        startLocationMonitor();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Google Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        isMonitoring = false;
        Log.e(TAG, "Connection Failed:" + connectionResult.getErrorMessage());
    }
}
