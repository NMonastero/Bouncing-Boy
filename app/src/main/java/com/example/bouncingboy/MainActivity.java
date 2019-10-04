package com.example.bouncingboy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import static java.util.concurrent.TimeUnit.*;
import java.util.*;
import java.util.Timer;
//import java.util.Timer.scheduleAtFixedRate;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Runnable{

//    // creating timer task, timer
//    TimerTask tasknew = new TimerScheduleFixedRateDelay();
//    Timer timer = new Timer();
//
//    // scheduling the task at fixed rate delay
//      timer.scheduleAtFixedRate(tasknew,500,1000);

    public static void create() {
        double xD = (Math.random() * 2) + 0.1;
        double yD = (Math.random() * 2) + 0.1;
        float x = (float) xD;
        float y = (float) yD;
        move(x, y, goon);
    }

    @Override
    public static void run(){
        create();
    }

    public static void move(float x, float y){
        if(goon.getX() + 350 >= x + 1000 || goon.getX() - x < 0){
            x = x * -1;
        }
        if(goon.getY() + 250 >= y + 1000 || goon.getY() - y < 0){
            y = y * -1;
        }

        goon.setX(goon.getX()+x);
        goon.setY(goon.getY()+y);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = findViewById(R.id.startButton);
        final ImageButton goon = findViewById(R.id.goon);

        goon.setX(goon.getX()+x);
        goon.setY(goon.getY()+y);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int time = 0;

                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

                while(time < 6000){
                    executorService.schedule(Move.run(), (long) 100, MILLISECONDS);
                    time++;
                }
            }
        });
    }

}

