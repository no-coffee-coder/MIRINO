package com.larebshaikh.mirino;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.graphics.Color.*;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity {



    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        Drawable progressDrawable = progressBar.getProgressDrawable().mutate();
        progressDrawable.setColorFilter(Color.rgb(255,191,51), PorterDuff.Mode.MULTIPLY);
        progressBar.setProgressDrawable(progressDrawable);
        progressAnimation();
    }

    void progressAnimation(){
        ProgressBarAnimation anim= new ProgressBarAnimation( this, progressBar,textView, 0f, 100f);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }




}
