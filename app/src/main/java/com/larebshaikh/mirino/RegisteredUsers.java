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
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class RegisteredUsers extends AdminsOptionMenu {
    private Toolbar admintoolbar;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference ref;
    String latitude, longitude;
    private RecyclerView usersRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<UserDetails, RegisteredUsers.UserViewHolder> usersRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_users);
        admintoolbar = findViewById(R.id.usertoolbar);
        mAuth=FirebaseAuth.getInstance();
        setSupportActionBar(admintoolbar);
        getSupportActionBar().setTitle("Registered Users");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.keepSynced(true);

        usersRV = (RecyclerView) findViewById(R.id.myRecyclview);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child("Users");
        Query personsQuery = personsRef.orderByKey();

        usersRV.hasFixedSize();
        usersRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<UserDetails>().setQuery(personsQuery, UserDetails.class).build();


        usersRVAdapter = new FirebaseRecyclerAdapter<UserDetails, RegisteredUsers.UserViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(RegisteredUsers.UserViewHolder holder, final int position, final UserDetails model) {
                holder.setuserName(model.getName());
                holder.setuserAge(model.getAge());
                holder.setuserPhone(model.getPhone());
                holder.setuserEmail(model.getEmail());
                String uid=model.getUid();
            }
                @Override
                public RegisteredUsers.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.usercardholder, parent, false);

                    return new RegisteredUsers.UserViewHolder(view);
                }
            };


        usersRV.setAdapter(usersRVAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        usersRVAdapter.startListening();


    }


    @Override
    protected void onStop() {
        super.onStop();
        usersRVAdapter.startListening();
    }



    public static class UserViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public UserViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setuserName(String usrname){
            TextView username = (TextView)mView.findViewById(R.id.userName);
            username.setText(usrname);
        }
        public void setuserPhone(String usrphone){
            TextView userphone = (TextView)mView.findViewById(R.id.userPhone);
            userphone.setText(usrphone);
        }
        public void setuserEmail(String usremail){
            TextView useremail = (TextView) mView.findViewById(R.id.userEmail);
            useremail.setText(usremail);
        }
        public void setuserAge(String auserge){
            TextView userage=(TextView) mView.findViewById(R.id.userAge);
            userage.setText(auserge);
        }
    }


    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MosqueVerifyActivity.class));

    }
}

