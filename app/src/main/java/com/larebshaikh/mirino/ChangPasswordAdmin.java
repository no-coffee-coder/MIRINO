package com.larebshaikh.mirino;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangPasswordAdmin extends AdminsOptionMenu {
    private EditText etmailchangepass;
    private Button btnChangePass;
    private FirebaseAuth mAuth;
    private Toolbar admintoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_password_admin);
        admintoolbar=findViewById(R.id.usertoolbar);
        setSupportActionBar(admintoolbar);

        etmailchangepass = findViewById(R.id.etMailChangePass);
        btnChangePass = findViewById(R.id.btnChangePassword);

        mAuth = FirebaseAuth.getInstance();



        btnChangePass.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (validate()) {
                    if (user != null) {
                        user.updatePassword(etmailchangepass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ChangPasswordAdmin.this, "Password Changed Successfully!", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(ChangPasswordAdmin.this, MosqueVerifyActivity.class));
                                } else {
                                    Toast.makeText(ChangPasswordAdmin.this, "Try Again After Sometime !!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }

        });

    }



    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MosqueVerifyActivity.class));

    }

    private boolean validate() {
        boolean result=false;
        String newpass=etmailchangepass.getText().toString();
        if (newpass.isEmpty()) {
            etmailchangepass.setError(getString(R.string.input_error_password));
            etmailchangepass.requestFocus();
            return false;

        } else if (newpass.length() < 8) {
            etmailchangepass.setError(getString(R.string.input_error_password_length));
            etmailchangepass.requestFocus();
            return false;

        } else{
            result =true;
        }
        return result;
    }
}
