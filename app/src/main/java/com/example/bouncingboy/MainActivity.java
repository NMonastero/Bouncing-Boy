package com.example.bouncingboy;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
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

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    // creating timer task, timer
//    TimerTask tasknew = new TimerScheduleFixedRateDelay();
//    Timer timer = new Timer();
//
//    // scheduling the task at fixed rate delay
//      timer.scheduleAtFixedRate(tasknew,500,1000);

    double xD = (Math.random()*2)+0.1;
    double yD = (Math.random()*2)+0.1;
    float x = (float) xD;
    float y = (float) yD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = findViewById(R.id.startButton);
        final ImageButton goon = findViewById(R.id.goon);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int time = 0;

                ScheduledExecutorService executorService = MainActivity.delay();

                while(time < 6000){
                //while(goon.getX() <= x + 1000 && goon.getX() - x >= 0 && goon.getY() <= y + 2000 && goon.getY() - y >= 0){




                    executorService.schedule(Movement.move(x, y, goon), (long) 100, MILLISECONDS)
                    //(Movement.move(x, y, goon), (long) 100, MILLISECONDS)

//                    MainActivity.this.sleep();
//                    ObjectAnimator animation = ObjectAnimator.ofFloat(goon, "translationX", 100f);
//                    animation.setDuration(1000);
//                    animation.start();
                    time++;
                }
            }
        });
    }
    public static java.util.concurrent.ScheduledExecutorService delay(){

    }


}

