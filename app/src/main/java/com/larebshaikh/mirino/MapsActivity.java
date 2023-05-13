package com.larebshaikh.mirino;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double lati,longi;
    Location location;
    MarkerOptions place1;
    String mosqueName,mosqueArea,fajrTime,zoharTime,asrTime,maghribTime,ishaTime,jumaTime;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getIncomingIntents();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng marker=new LatLng(18.9618, 72.8167);
        mMap.addMarker(new MarkerOptions().position(marker).title("Current Location"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(18.9618,72.8167))
                .zoom(15)
                .bearing(0)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 5000, null);

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            public void onMapClick(LatLng point){
                lati=point.latitude;
                longi=point.longitude;

                Toast.makeText(MapsActivity.this,"Latitude"+
                        point.latitude + ", Longitude" + point.longitude,
                        Toast.LENGTH_SHORT).show();
                mMap.clear();
                LatLng newPlace=new LatLng(lati,longi);
                mMap.addMarker(new MarkerOptions().position(newPlace).title("Mosque"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(newPlace));

                reqAddMosque(mosqueName,mosqueArea,fajrTime,zoharTime,asrTime,maghribTime,ishaTime,jumaTime,lati,longi);
            }
        });
    }

    private void reqAddMosque(String mosqueName, String mosqueArea, String fajrTime, String zoharTime, String asrTime, String maghribTime, String ishaTime, String jumaTime, Double lati, Double longi) {
        Map<String, String> tempmosque = new HashMap<>();
        String templati=Double.toString(lati);
        String templongi=Double.toString(longi);
        tempmosque.put("MosqueName", mosqueName);
        tempmosque.put("MosqueArea", mosqueArea);
        tempmosque.put("Fajr", fajrTime);
        tempmosque.put("Zohar", zoharTime);
        tempmosque.put("Asr",asrTime);
        tempmosque.put("Maghrib",maghribTime);
        tempmosque.put("Isha",ishaTime);
        tempmosque.put("Juma",jumaTime);
        tempmosque.put("MosqueLongitude",templongi);
        tempmosque.put("MosqueLatitude",templati);

        FirebaseDatabase.getInstance().getReference("TempMosque")
                .child(mosqueName)
                .setValue(tempmosque).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MapsActivity.this,MosqueSearchActivity.class));
                    Toast.makeText(MapsActivity.this, "Request Added Successfully.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MapsActivity.this, (task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getIncomingIntents(){
        if(getIntent().hasExtra("MosqueName")&&getIntent().hasExtra("MosqueArea")&&getIntent().hasExtra("fajr")
                &&getIntent().hasExtra("zohar")&&getIntent().hasExtra("asr")&&getIntent().hasExtra("maghrib")
        &&getIntent().hasExtra("isha")&&getIntent().hasExtra("juma")){
            mosqueName=getIntent().getStringExtra("MosqueName");
            mosqueArea=getIntent().getStringExtra("MosqueArea");
            fajrTime=getIntent().getStringExtra("fajr");
            zoharTime=getIntent().getStringExtra("zohar");
            asrTime=getIntent().getStringExtra("asr");
            maghribTime=getIntent().getStringExtra("maghrib");
            ishaTime=getIntent().getStringExtra("isha");
            jumaTime=getIntent().getStringExtra("juma");
        }
    }

}
