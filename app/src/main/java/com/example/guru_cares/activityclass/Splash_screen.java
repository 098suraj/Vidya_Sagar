package com.example.guru_cares.activityclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.guru_cares.R;

public class Splash_screen extends AppCompatActivity {
    final private static int SPLASH=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intents=new Intent(Splash_screen.this, Entry1.class);
                startActivity(intents);
                finish();
            }
        },SPLASH);

    }

}