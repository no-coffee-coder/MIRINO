package com.larebshaikh.mirino;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ForgotPassword2 extends AppCompatActivity {

    private EditText etforgotpassmail;
    private Button btnresetPass;
    private TextView tvgotoLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        etforgotpassmail = findViewById(R.id.etForgotPassEmail);
        btnresetPass = findViewById(R.id.btnForgotPass);
        tvgotoLogin = findViewById(R.id.gotoLogin);
        mAuth = FirebaseAuth.getInstance();

        tvgotoLogin.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               startActivity(new Intent(ForgotPassword2.this, LoginMainActivity.class));
                                           }
                                       }
        );

        btnresetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    rstPass(etforgotpassmail.getText().toString());
                }
            }
        });
    }

    private void rstPass(String mail){
        mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword2.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(ForgotPassword2.this, LoginMainActivity.class));
                }else{
                    Toast.makeText(ForgotPassword2.this, "Password Not Exist Check Again!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Boolean Validate(){

        String email=etforgotpassmail.getText().toString().trim();

        Boolean result = false;
        if (email.isEmpty()) {
            etforgotpassmail.setError(getString(R.string.input_error_email));
            etforgotpassmail.requestFocus();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etforgotpassmail.setError(getString(R.string.input_error_email_invalid));
            etforgotpassmail.requestFocus();
            return false;
        }
        else{
            result= true;
            return result;
        }
    }


}
