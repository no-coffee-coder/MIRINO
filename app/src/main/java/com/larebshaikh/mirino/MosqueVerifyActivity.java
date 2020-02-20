package com.larebshaikh.mirino;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.Toast;


import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class MosqueVerifyActivity extends AdminsOptionMenu {
    private Toolbar admintoolbar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    private ProgressBar prgmosquevrfy;

    private RecyclerView mosqueVerifyRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<MosqueDetails, MosqueVerifyActivity.MosqueViewHolder> mosqueVerifyRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosque_verify);
        admintoolbar=findViewById(R.id.usertoolbar);
        prgmosquevrfy=findViewById(R.id.prgmosqueverify);
        setSupportActionBar(admintoolbar);
        getSupportActionBar().setTitle("Mosque Verify");




        mDatabase = FirebaseDatabase.getInstance().getReference().child("TempMosque");
        mDatabase.keepSynced(true);

        mosqueVerifyRV = (RecyclerView) findViewById(R.id.myRecyclview);
        prgmosquevrfy.setVisibility(View.VISIBLE);
        DatabaseReference mosqueRef = FirebaseDatabase.getInstance().getReference().child("TempMosque");
        Query mosqueverifyQuery = mosqueRef.orderByKey();

        mosqueVerifyRV.hasFixedSize();
        mosqueVerifyRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<MosqueDetails>().setQuery(mosqueverifyQuery, MosqueDetails.class).build();


        mosqueVerifyRVAdapter = new FirebaseRecyclerAdapter<MosqueDetails, MosqueVerifyActivity.MosqueViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(MosqueVerifyActivity.MosqueViewHolder holder, final int position, final MosqueDetails model) {
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
                        final String mosquename=model.getMosqueName();
                        final String mosquearea=model.getMosqueArea();
                        final String fajr=model.getFajr();
                        final String zohar=model.getZohar();
                        final String asr=model.getAsr();
                        final String maghrib=model.getMaghrib();
                        final String isha=model.getIsha();
                        final String juma=model.getJuma();
                        final String latitude=model.getMosqueLatitude();
                        final String longitude=model.getMosqueLongitude();

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MosqueVerifyActivity.this);
                        alertDialogBuilder.setTitle("Verify Mosque "+mosquename);
                        alertDialogBuilder
                                .setMessage("Accept or Reject Request")
                                .setCancelable(false)
                                .setPositiveButton("Accept Request",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Map<String, String> mosque = new HashMap<>();

                                                mosque.put("MosqueName", mosquename);
                                                mosque.put("MosqueArea", mosquearea);
                                                mosque.put("Fajr", fajr);
                                                mosque.put("Zohar", zohar);
                                                mosque.put("Asr",asr);
                                                mosque.put("Maghrib",maghrib);
                                                mosque.put("Isha",isha);
                                                mosque.put("Juma",juma);
                                                mosque.put("MosqueLongitude",longitude);
                                                mosque.put("MosqueLatitude",latitude);

                                                FirebaseDatabase.getInstance().getReference("Mosque")
                                                        .child(mosquename)
                                                        .setValue(mosque).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(MosqueVerifyActivity.this, "Verified Successfully.", Toast.LENGTH_LONG).show();
                                                            DatabaseReference dltempMosque=FirebaseDatabase.getInstance().getReference("TempMosque").child(mosquename);
                                                            dltempMosque.removeValue();
                                                            Toast.makeText(MosqueVerifyActivity.this,"Removed The Values from Temp Table",Toast.LENGTH_SHORT).show();

                                                        } else {
                                                            Toast.makeText(MosqueVerifyActivity.this, (task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });dialog.cancel();
                                            }
                                        })

                                .setNegativeButton("Remove Request", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        DatabaseReference dltempMosque=FirebaseDatabase.getInstance().getReference("TempMosque").child(mosquename);
                                        dltempMosque.removeValue();
                                        Toast.makeText(MosqueVerifyActivity.this,"Removed The Values from Temp Table",Toast.LENGTH_SHORT).show();

                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                });

            }

            @Override
            public MosqueVerifyActivity.MosqueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.mosquecardholder, parent, false);

                return new MosqueVerifyActivity.MosqueViewHolder(view);
            }
        };


        mosqueVerifyRV.setAdapter(mosqueVerifyRVAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        prgmosquevrfy.setVisibility(View.VISIBLE);
        mosqueVerifyRVAdapter.startListening();
        prgmosquevrfy.setVisibility(View.GONE);

    }


    @Override
    protected void onStop() {
        super.onStop();
        prgmosquevrfy.setVisibility(View.GONE);
        mosqueVerifyRVAdapter.stopListening();
        prgmosquevrfy.setVisibility(View.VISIBLE);
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
}
