package com.example.a20151inf0182.organizeme.Activity;

import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a20151inf0182.organizeme.R;

import org.w3c.dom.Text;

public class cronometro_activity extends AppCompatActivity {
    private countDown timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro_activity);
        TextView tvMostrarTempo = (TextView) findViewById(R.id.tempoVer);
        tvMostrarTempo.setText("25:00");


    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tv = (TextView) findViewById(R.id.tempoVer);
        timer = new countDown(this, tv, 5*1000, 1000);
        timer.start();
    }
}
