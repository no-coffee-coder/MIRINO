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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class RegisterUser extends AppCompatActivity {

    private EditText etName, etAge, etEmail, etConfirmPass, etPass, etMobileNumber;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;


    private Button btnRegister;
    private TextView tvgotologin;

    String name,email,phone,Age,Password, ConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        setupUIViews();
        mAuth = FirebaseAuth.getInstance();



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    //Upload data to the database
                    final String user_email = etEmail.getText().toString().trim();
                    final String user_Name = etName.getText().toString().trim();
                    final String user_Age = etAge.getText().toString().trim();
                    final String user_Phone = etMobileNumber.getText().toString().trim();
                    String user_password = etPass.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(RegisterUser.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                sendUserData(user_Name,user_Age,user_email,user_Phone);
                                finish();
                                startActivity(new Intent(RegisterUser.this, LoginMainActivity.class));
                            }else{
                                Toast.makeText(RegisterUser.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


        tvgotologin.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               startActivity(new Intent(RegisterUser.this,LoginMainActivity.class));
                                           }
                                       }

        );
    }

    private void setupUIViews(){
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etConfirmPass = findViewById(R.id.etConfirmPass);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPasslogin);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        progressBar = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
        tvgotologin = findViewById(R.id.tvgotologin);
        btnRegister = findViewById(R.id.btnRegister);
    }


    private Boolean validate() {
        Boolean result = false;

        final String name = etName.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        Password = etPass.getText().toString().trim();
        ConfirmPassword = etConfirmPass.toString().trim();
        String phone = etMobileNumber.getText().toString().trim();
        String Age = etAge.getText().toString().trim();


        if (name.isEmpty()) {
            etName.setError(getString(R.string.input_error_name));
            etName.requestFocus();
            return false;

        } else if (email.isEmpty()) {
            etEmail.setError(getString(R.string.input_error_email));
            etEmail.requestFocus();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.input_error_email_invalid));
            etEmail.requestFocus();
            return false;

        } else if (Password.isEmpty()) {
            etPass.setError(getString(R.string.input_error_password));
            etPass.requestFocus();
            return false;

        } else if (Password.length() < 8) {
            etPass.setError(getString(R.string.input_error_password_length));
            etPass.requestFocus();
            return false;

            //} else if (Password != ConfirmPassword) {
            // etConfirmPass.setError(getString(R.string.input_error_password_notmatched));
            //  etConfirmPass.requestFocus();
            //  return false;
        } else if (phone.isEmpty()) {
            etMobileNumber.setError(getString(R.string.input_error_phone));
            etMobileNumber.requestFocus();
            return false;
        } else if (phone.length() != 10) {
            etMobileNumber.setError(getString(R.string.input_error_phone_invalid));
            etMobileNumber.requestFocus();
            return false;
        } else if (Age.isEmpty()) {
            etMobileNumber.setError(getString(R.string.input_error_Age));
            etMobileNumber.requestFocus();
            return false;
        } else if (Age.length() > 3) {
            etMobileNumber.setError(getString(R.string.input_error_Age_invalid));
            etMobileNumber.requestFocus();
            return false;
        } else {
            result = true;
            return result;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(this,MosqueSearchActivity.class));

        }
    }


    private void sendUserData(String userName,String userAge,String userMail, String userPhone) {
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> user = new HashMap<>();

        user.put("Name", userName);
        user.put("Age", userAge);
        user.put("Email", userMail);
        user.put("isAdmin", "False");
        user.put("Phone",userPhone);
        FirebaseDatabase.getInstance().getReference("Users")
                .child((FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    new Intent(RegisterUser.this,MosqueSearchActivity.class);
                    Toast.makeText(RegisterUser.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterUser.this, (task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,LoginMainActivity.class));

    }



}
