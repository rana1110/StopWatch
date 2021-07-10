package com.cnb.stopwatchapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class ChronometerStopWatchActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean isResume;
    long tMilliSec, tStart, tBuff, tUpdate = 0L;
    int sec, min, milliSec;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_watch_main_activity);
        chronometer = findViewById(R.id.my_chrono_meter);
        handler = new Handler();


    }

    public void onClickStart(View view) {
        if (!isResume) {
            tStart = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            chronometer.start();
            isResume = true;
        }

    }

    public void onClickStop(View view) {
        tBuff += tMilliSec;
        handler.removeCallbacks(runnable);
        chronometer.stop();
        isResume = false;
    }

    @SuppressLint("SetTextI18n")
    public void onClickReset(View view) {
        handler.removeCallbacks(runnable);
        chronometer.stop();
        isResume = false;
        chronometer.setText("00:00:00");

    }

    public Runnable runnable = new Runnable() {
        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        @Override
        public void run() {
            tMilliSec = SystemClock.uptimeMillis() - tStart;
            tUpdate = tBuff + tMilliSec;
            sec = (int) (tUpdate / 1000);
            min = sec / 60;
            sec = sec % 60;
            milliSec = (int) (tUpdate % 100);
            chronometer.setText(String.format("%02d", min) + ":" + String.format("%02d", sec) + ":" + String.format("%02d", milliSec));
            handler.postDelayed(this, 60);
        }
    };

}