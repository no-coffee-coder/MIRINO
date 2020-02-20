package com.larebshaikh.mirino;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MosqueSearchActivity extends UsersOptionMenu {

    private Toolbar usertoolbar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    FirebaseListAdapter adapter;
    RecyclerView mosquerecycleview;
    ListView mosquelistview;
    SearchView mosquesearchView;
    String latitude, longitude;
    private ProgressBar prgmosquesrch;
    private RecyclerView mosqueSearchRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<MosqueDetails, MosqueSearchActivity.MosqueViewHolder> mosqueSearchRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosque_search);
        usertoolbar = findViewById(R.id.usertoolbar);
        prgmosquesrch=findViewById(R.id.prgmosquesearch);
        setSupportActionBar(usertoolbar);
        getSupportActionBar().setTitle("Search Mosque");

        prgmosquesrch.setVisibility(View.VISIBLE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Mosque");
        mDatabase.keepSynced(true);

        mosqueSearchRV = (RecyclerView) findViewById(R.id.myRecycleView);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child("Mosque");
        Query personsQuery = personsRef.orderByKey();

        mosqueSearchRV.hasFixedSize();
        mosqueSearchRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<MosqueDetails>().setQuery(personsQuery, MosqueDetails.class).build();


        mosqueSearchRVAdapter = new FirebaseRecyclerAdapter<MosqueDetails, MosqueSearchActivity.MosqueViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(MosqueSearchActivity.MosqueViewHolder holder, final int position, final MosqueDetails model) {
                holder.setMosqueName(model.getMosqueName());
                holder.setMosqueArea(model.getMosqueArea());
                holder.setFajr(model.getFajr());
                holder.setZohar(model.getZohar());
                holder.setAsr(model.getAsr());
                holder.setMaghrib(model.getMaghrib());
                holder.setIsha(model.getIsha());
                holder.setJuma(model.getJuma());
                String Longitude=model.getMosqueLongitude();
                String Latitude=model.getMosqueLatitude();

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String latitude = model.getMosqueLatitude();
                        final String longitude = model.getMosqueLongitude();
                        Intent intent = new Intent(getApplicationContext(), MapsFromTo.class);
                        intent.putExtra("latitude", latitude);
                        intent.putExtra("longitude",longitude);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public MosqueSearchActivity.MosqueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.mosquecardholder, parent, false);

                return new MosqueSearchActivity.MosqueViewHolder(view);
            }

        };


        mosqueSearchRV.setAdapter(mosqueSearchRVAdapter);

    }






    @Override
    public void onStart() {
        super.onStart();
        prgmosquesrch.setVisibility(View.VISIBLE);
        mosqueSearchRVAdapter.startListening();
        prgmosquesrch.setVisibility(View.GONE);


    }


    @Override
    protected void onStop() {
        super.onStop();
        prgmosquesrch.setVisibility(View.GONE);
        mosqueSearchRVAdapter.stopListening();
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



public static class MosqueViewHolder extends RecyclerView.ViewHolder{
    View mView;
    public MosqueViewHolder(View itemView){
        super(itemView);
        mView = itemView;
    }
    public void setMosqueName(String name){
        TextView mosquename = (TextView)mView.findViewById(R.id.mosquename);
        mosquename.setText(name);
    }
    public void setMosqueArea(String area){
        TextView mosquearea = (TextView)mView.findViewById(R.id.mosquearea);
        mosquearea.setText(area);
    }
    public void setFajr(String fajr){
        TextView fajrtime = (TextView) mView.findViewById(R.id.FajrTime);
        fajrtime.setText(fajr);
    }
    public void setZohar(String zohar){
        TextView zohartime=(TextView) mView.findViewById(R.id.ZoharTime);
        zohartime.setText(zohar);
    }
    public void setAsr(String asr){
        TextView asrtime=(TextView) mView.findViewById(R.id.AsrTime);
        asrtime.setText(asr);
    }
    public void setMaghrib(String maghrib){
        TextView maghribtime=(TextView) mView.findViewById(R.id.MaghribTime);
        maghribtime.setText(maghrib);
    }
    public void setIsha(String isha){
        TextView ishatime=(TextView) mView.findViewById(R.id.IshaTime);
        ishatime.setText(isha);
    }
    public void setJuma(String juma){
        TextView jumatime=(TextView) mView.findViewById(R.id.jumaTime);
        jumatime.setText(juma);
    }
}
}


