package com.larebshaikh.mirino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends UsersOptionMenu {
    private Toolbar mtoolbar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private String userName,userAge,userPhone,userEmail;
    private TextView etname,etage,etphone,etmail;
    private ProgressBar progressbar;
    private Button btngotochangepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mtoolbar=findViewById(R.id.usertoolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("My Profile");
        progressbar=findViewById(R.id.prgprofile);

        progressbar.setVisibility(View.VISIBLE);

        etname=findViewById(R.id.tvName);
        etage=findViewById(R.id.tvAge);
        etphone=findViewById(R.id.tvPhone);
        etmail=findViewById(R.id.tvEmail);
        btngotochangepass=findViewById(R.id.btngotochangepass);
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        final DatabaseReference databaseReference=firebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child("Name").getValue().toString();
                userAge = dataSnapshot.child("Age").getValue().toString();
                userEmail = dataSnapshot.child("Email").getValue().toString();
                userPhone =dataSnapshot.child("Phone").getValue().toString();


        etname.setText(getString(R.string.nam)+userName);
        etage.setText(getString(R.string.age)+userAge);
        etphone.setText(getString(R.string.mobnumer)+userPhone);
        etmail.setText(getString(R.string.Emaail)+userEmail);
        progressbar.setVisibility(View.GONE);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserProfileActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        btngotochangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(UserProfileActivity.this,ChangePasswordActivity.class));
            }
        });

}

}
