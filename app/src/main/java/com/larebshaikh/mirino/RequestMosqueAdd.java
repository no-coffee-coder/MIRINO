package com.larebshaikh.mirino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;


public class RequestMosqueAdd extends UsersOptionMenu {

    private Toolbar usertoolbar;
    private EditText etmosquename,etmosquearea,etfajrtime,etzohartime,etasrtime,etmaghribtime,etishatime,etjumatime;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button btnreqaddmosque;
    String name,area,fajr,zohar,asr,maghrib,isha,juma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_mosque_add);
        usertoolbar=findViewById(R.id.usertoolbar);
        setSupportActionBar(usertoolbar);
        getSupportActionBar().setTitle("Request to Add Mosque");

        setupUIViews();

        btnreqaddmosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    Intent intent=new Intent(RequestMosqueAdd.this,MapsActivity.class);
                    intent.putExtra("MosqueName",name);
                    intent.putExtra("MosqueArea",area);
                    intent.putExtra("fajr",fajr);
                    intent.putExtra("zohar",zohar);
                    intent.putExtra("maghrib",maghrib);
                    intent.putExtra("asr",asr);
                    intent.putExtra("isha",isha);
                    intent.putExtra("juma",juma);
                    startActivity(intent);


                }
            }
        });

    }


    private void setupUIViews(){
        etmosquename = findViewById(R.id.etMosqueName);
        etmosquearea = findViewById(R.id.etMosqueArea);
        etfajrtime = findViewById(R.id.etFajrTime);
        etzohartime = findViewById(R.id.etZoharTime);
        etasrtime = findViewById(R.id.etAsrTIme);
        etmaghribtime = findViewById(R.id.etMaghribTime);
        etishatime= findViewById(R.id.etIshaTime);
        etjumatime= findViewById(R.id.etJumaTime);
        progressBar = findViewById(R.id.addmosqueprgbar);
        mAuth = FirebaseAuth.getInstance();
        btnreqaddmosque = findViewById(R.id.btnrequestmosqueadd);
    }


    private Boolean validate() {
        Boolean result = false;

        name = etmosquename.getText().toString().trim();
        area = etmosquearea.getText().toString().trim();
        fajr = etzohartime.getText().toString().trim();
        zohar = etzohartime.getText().toString().trim();
        asr = etasrtime.getText().toString().trim();
        maghrib = etmaghribtime.getText().toString().trim();
        isha = etishatime.getText().toString().trim();
        juma = etjumatime.getText().toString().trim();


        if (name.isEmpty()) {
            etmosquename.setError("Mosque Name is Required.");
            etmosquename.requestFocus();
            return false;

        } else if (name.length() < 4) {
            etmosquename.setError("Mosque Name should be Greater then 4 characters.");
            etmosquename.requestFocus();
            return false;
        } else if (area.isEmpty()) {
            etmosquearea.setError("Mosque Area is Required.");
            etmosquearea.requestFocus();
            return false;
        } else if (area.length() < 4) {
            etmosquearea.setError("Mosque Area should be Greater then 4 characters.");
            etmosquearea.requestFocus();
            return false;
        } else if (fajr.isEmpty()) {
            etfajrtime.setError("Fajr Time is Required.");
            etfajrtime.requestFocus();
            return false;
        } else if (fajr.length() < 4) {
            etfajrtime.setError("Fajr Time should be Greater then 4 characters.");
            etfajrtime.requestFocus();
            return false;
        } else if (zohar.isEmpty()) {
            etzohartime.setError("Zohar Time is Required.");
            etzohartime.requestFocus();
            return false;
        } else if (zohar.length() < 4) {
            etzohartime.setError("Zohar should be Greater then 4 characters.");
            etzohartime.requestFocus();
        } else if (asr.isEmpty()) {
            etasrtime.setError("Asr Time is Required.");
            etasrtime.requestFocus();
            return false;
        } else if (asr.length() < 4) {
            etasrtime.setError("Asr Time should be Greater then 4 characters.");
            etasrtime.requestFocus();
            return false;
        } else if (maghrib.isEmpty()) {
            etmaghribtime.setError("Maghrib Time is Required.");
            etmaghribtime.requestFocus();
            return false;
        } else if (maghrib.length() < 4) {
            etmaghribtime.setError("Maghrib Time should be Greater then 4 characters.");
            etmaghribtime.requestFocus();
            return false;
        } else if (isha.isEmpty()) {
            etishatime.setError("Isha Time is Required.");
            etishatime.requestFocus();
            return false;
        } else if (isha.length() < 4) {
            etishatime.setError("Isha Time should be Greater then 4 characters.");
            etishatime.requestFocus();
            return false;
        } else if (juma.isEmpty()) {
            etjumatime.setError("Juma Time is Required.");
            etjumatime.requestFocus();
            return false;
        } else if (juma.length() < 4) {
            etjumatime.setError("Juma Time should be Greater then 4 characters.");
            etjumatime.requestFocus();
            return false;
        } else {
            result = true;
            return result;
        }
        return result;
    }
}
