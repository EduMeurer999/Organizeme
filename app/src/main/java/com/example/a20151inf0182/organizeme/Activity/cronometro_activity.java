package com.example.a20151inf0182.organizeme.Activity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20151inf0182.organizeme.R;

import org.w3c.dom.Text;

import java.util.Calendar;

public class cronometro_activity extends AppCompatActivity {
     public TextView tvMinutes;
     public static TextView tvSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro_activity);
        tvMinutes = (TextView) findViewById(R.id.minutos);
        tvSeconds= (TextView) findViewById(R.id.segundos);

        Button btnParar = (Button) findViewById(R.id.btnParar);
        Button btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempoTrabalho();
            }
        });


    }
    public void tempoTrabalho(){
        new CountDownTimer(25*1000*60, 60*1000) {

            @Override
            public void onTick(long minutes) {

                new CountDownTimer(60*1000, 1000) {

                    public void onTick(long seconds) {
                        tvMinutes.setTextColor(Color.RED);
                        tvSeconds.setTextColor(Color.RED);
                        if((seconds/1000) <10){
                            tvSeconds.setText("0"+seconds/1000);
                        }
                        else {
                            tvSeconds.setText(""+seconds/1000);
                        }

                    }

                    public void onFinish() {

                    }
                }.start();
                tvMinutes.setText(""+(minutes/1000)/60);
            }



            @Override
            public void onFinish() {
                final MediaPlayer mp = MediaPlayer.create(cronometro_activity.this, R.raw.som);
                mp.start();
                Toast.makeText(cronometro_activity.this, "Hora de Descansar", Toast.LENGTH_SHORT).show();
                tvMinutes.setTextColor(Color.GREEN);
                tvSeconds.setTextColor(Color.GREEN);
                tempoDescanso();

            }
        }.start();
    }

    public void tempoDescanso(){
        new CountDownTimer(5*1000*60, 60*1000) {

            @Override
            public void onTick(long minutes) {

                new CountDownTimer(60*1000, 1000) {

                    public void onTick(long seconds) {
                        tvMinutes.setTextColor(Color.GREEN);
                        tvSeconds.setTextColor(Color.GREEN);
                        if((seconds/1000) <10){
                            tvSeconds.setText("0"+seconds/1000);
                        }
                        else {
                            tvSeconds.setText(""+seconds/1000);
                        }

                    }

                    public void onFinish() {

                    }
                }.start();
                tvMinutes.setText(""+(minutes/1000)/60);
            }



            @Override
            public void onFinish() {
                final MediaPlayer mp = MediaPlayer.create(cronometro_activity.this, R.raw.som);
                mp.start();
                Toast.makeText(cronometro_activity.this, "Hora de trabalhar!", Toast.LENGTH_SHORT).show();
                tvMinutes.setTextColor(Color.RED);
                tvSeconds.setTextColor(Color.RED);
                tempoTrabalho();
                }


        }.start();
    }

}
