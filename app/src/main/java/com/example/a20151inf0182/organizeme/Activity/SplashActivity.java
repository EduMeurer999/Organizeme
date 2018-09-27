package com.example.a20151inf0182.organizeme.Activity;

//import android.content.Intent;
//import android.os.Handler;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a20151inf0182.organizeme.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }//fim run
        }, 3000);
    }
}
