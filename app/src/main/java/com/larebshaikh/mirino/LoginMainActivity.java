package com.larebshaikh.mirino;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginMainActivity  extends AppCompatActivity {


    TextView gotoregister, gotoForgotPassword;
    ProgressBar progressBar;
    EditText etUserName, etPassword;
    Button btnLogin;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);



        etPassword =findViewById(R.id.etPasslogin);
        etUserName = findViewById(R.id.etUsername);
        gotoregister=findViewById(R.id.tvGoToSignUp);
        gotoForgotPassword=findViewById(R.id.tvGoToForgotPassword);
        progressBar = findViewById(R.id.progressBar3);
        btnLogin = findViewById(R.id.btnLogin);


        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        if(user != null){
            firebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());
            DatabaseReference databaseReference=firebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String Admin = dataSnapshot.child("isAdmin").getValue().toString();
                    if(Admin.equals("True")){
                        Toast.makeText(LoginMainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginMainActivity.this,MosqueVerifyActivity.class));
                        finish();
                    }else if(Admin.equals("False")){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginMainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginMainActivity.this,ChooseFunctionActivity.class));
                        finish();
                    }else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginMainActivity.this, "Incorrect UserEmail or Password", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick (View view){
                if(verifyEmail()){
                    validate(etUserName.getText().toString(),etPassword.getText().toString());
                }
            }

        });



        gotoregister.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                startActivity(new Intent(LoginMainActivity.this,RegisterUser.class));
                                            }
                                        }
        );

        gotoForgotPassword.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      startActivity(new Intent(LoginMainActivity.this,ForgotPassword2.class));
                                                  }
                                              }
        );
    }
    private void validate(String userName,String userPassword) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());
                    DatabaseReference databaseReference=firebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String Admin = dataSnapshot.child("isAdmin").getValue().toString();
                            if(Admin.equals("True")){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginMainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginMainActivity.this,MosqueVerifyActivity.class));
                                finish();
                            }else if(Admin.equals("False")){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginMainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginMainActivity.this,MosqueSearchActivity.class));
                                finish();
                            }else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginMainActivity.this, "Incorrect UserEmail or Password", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


    }



    private Boolean verifyEmail(){

        String email=etUserName.getText().toString().trim();
        String Password=etPassword.getText().toString().trim();

        boolean result;
        if (email.isEmpty()) {
            etUserName.setError(getString(R.string.input_error_email));
            etUserName.requestFocus();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etUserName.setError(getString(R.string.input_error_email_invalid));
            etUserName.requestFocus();
            return false;
        }else if (Password.isEmpty()) {
            etPassword.setError(getString(R.string.input_error_password));
            etPassword.requestFocus();
            return false;
        }else{
            result= true;
            return result;
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
