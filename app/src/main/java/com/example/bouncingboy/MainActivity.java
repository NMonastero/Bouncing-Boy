package com.example.bouncingboy;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
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

public class MainActivity extends AppCompatActivity implements Runnable {

    int score = 0;
    int highscore = 0;
    int time = 60;
    Movement move;

    void startTimer() {
        Executors.newSingleThreadScheduledExecutor().schedule(runnable, 0, TimeUnit.SECONDS);
    }

    @Override
    public void run(){
        System.out.println("Why is this called?");
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        move = new Movement(this);

//        Timer t= new Timer();
        final TextView scoreboard = findViewById(R.id.scoreboard);
        final Button startButton = findViewById(R.id.startButton);
        final ImageButton goon = findViewById(R.id.goon);
        final TextView timer = findViewById(R.id.timer);

//        @Override
//        public void run(){
//            System.out.println("Why is this called?");
//        }



//        goon.setX(goon.getX()+x);
//        goon.setY(goon.getY()+y);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0;
                scoreboard.setText("Score: " + score +"\nHighscore: " + highscore);
                time = 60;
                startTimer();
                timer.setText("Time Remaining: " + time);
                setContentView(move);
            }
        });

        goon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time > 0) {
                    score++;
                    if (score > highscore) {
                        highscore = score;
                    }
                    scoreboard.setText("Score: " + score + "\nHighscore: " + highscore);

                    double xD = (Math.random() * 950) + 0;
                    double yD = (Math.random() * 1350) + 100;
                    float x = (float) xD;
                    float y = (float) yD;

                    goon.setX(x);
                    goon.setY(y);
                }
            }
        });
    }

}

