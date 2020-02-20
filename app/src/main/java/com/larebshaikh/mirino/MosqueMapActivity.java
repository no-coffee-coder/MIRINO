package com.larebshaikh.mirino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MosqueMapActivity extends UsersOptionMenu {
    private Toolbar usertoolbar;
    TextView etlat, etlon;
    String lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosque_map);
        usertoolbar=findViewById(R.id.usertoolbar);
        setSupportActionBar(usertoolbar);
        usertoolbar.setTitle("Mosque Map");
        etlat=findViewById(R.id.lat);
        etlon=findViewById(R.id.lon);
        //getIncomingIntent();
        etlat.setText(lat);
        etlon.setText(lon);
    }

    /*public void getIncomingIntent(){
        if(getIntent().hasExtra("Longitude")&&getIntent().hasExtra("Latitude")){
            lat=getIntent().getStringExtra("Latitude");
            lon=getIntent().getStringExtra("Longitude");
        }
    }*/
}
