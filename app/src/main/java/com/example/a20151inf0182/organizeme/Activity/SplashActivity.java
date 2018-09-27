package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a20151inf0182.organizeme.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainActivity=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(MainActivity);
                finish();
            }//fim run
        }, SPLASH_TIME_OUT);
    }
}
