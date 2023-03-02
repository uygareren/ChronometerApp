package com.example.chronometer_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AppCompatButton StartBtn, StopBtn, ResetBtn;
    Chronometer chronometer;
    ImageView startImage;

    long pauseOffSet;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //Buttons
        StartBtn = findViewById(R.id.start);
        StopBtn = findViewById(R.id.stop);
        ResetBtn = findViewById(R.id.reset);

        // Chronometer
        chronometer = findViewById(R.id.chronometer);

        //
        startImage = findViewById(R.id.startImage);


        // START BUTTON
        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
                chronometer.stop();

                StartBtn.setVisibility(View.GONE);
                chronometer.start();

                StopBtn.setVisibility(View.VISIBLE);
                startImage.setImageResource(R.drawable.pause);


            }
        });

        // STOP BUTTON

        StopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                StopBtn.setVisibility(View.GONE);

                pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();

                StartBtn.setVisibility(View.VISIBLE);
                StartBtn.setText("Resume");
                startImage.setImageResource(R.drawable.start);




            }
        });



        // RESET BUTTON

        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StopBtn.setVisibility(View.GONE);
                StartBtn.setVisibility(View.VISIBLE);
                startImage.setImageResource(R.drawable.start);

                StartBtn.setText("Start");

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();

                pauseOffSet = 0;
            }
        });



    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
        super.onBackPressed();
    }
}