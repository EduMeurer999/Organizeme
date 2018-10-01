package com.example.a20151inf0182.organizeme.Activity;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Calendar;

public class countDown extends CountDownTimer {
    private Context context;
    private TextView tv;
    private long timeInFuture;
    private long interval;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public countDown(Context context, TextView tv, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeInFuture = millisUntilFinished;
        tv.setText(getCorrectTimer(true, millisUntilFinished)+":"+getCorrectTimer(false, millisUntilFinished));
    }

    @Override
    public void onFinish() {
        timeInFuture -= 1000;
        tv.setText(getCorrectTimer(true, timeInFuture)+":"+getCorrectTimer(false, timeInFuture));
    }

    private String getCorrectTimer(boolean isMinute, long millisUntilFinished){
        String aux;
        int consCalendar = isMinute ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);


        aux = c.get(consCalendar)<10 ? "0"+c.get(consCalendar): ""+c.get(consCalendar);
        return aux;
    }
}
