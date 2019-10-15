package com.example.bouncingboy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    AsteroidView asteroidView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asteroidView = new AsteroidView(this);
        setContentView(asteroidView);
    }

    class AsteroidView extends SurfaceView implements Runnable {
        Thread gameThread = null;
        SurfaceHolder ourHolder;
        volatile boolean playing;
        boolean paused = false;
        boolean bump = false;
        Canvas canvas;
        Paint paint;
        int y;
        int posx, posy;
        int dx, dy;
        int height, width;
        boulder[] b;
        paddle p;


        private long thisTimeFrame;
        public AsteroidView(Context context) {
            super(context);

            ourHolder = getHolder();
            paint = new Paint();
        }

        @Override
        public void run() {
            Random r = new Random();

            p = new paddle();
            p.x =500;
            p.y = 1250;
            p.dx = 50;
            p.dy = 0;

            b = new boulder[1];

            b[0] = new boulder();
//            b[0].x = r.nextInt(150)-50;
//            b[0].y = r.nextInt(150)-50;
//            b[0].dx = r.nextInt(150)-50;
//            b[0].dy = r.nextInt(150)-50;
            b[0].x = 100;
            b[0].y = 80;
            b[0].dx = 75;
            b[0].dy = 45;
            b[0].diameter = 95;
//            for (int i = 0; i < 1; ++i) {
//                b[i] = new boulder();
//                b[i].x = r.nextInt(50);
//                b[i].y = r.nextInt(50);
//                b[i].dx = r.nextInt(30) - 15;
//                b[i].dy = r.nextInt(30) - 15;
//                b[i].diameter = 95;
//            }


            while (playing)
            {
                if (!paused) {
                    update();
                }
                draw();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {

                }
            }
        }
        public void update() {
            y = y + 5;
            if (y > 200)
                y = 5;

            posx += dx;
            posy += dy;
//            if ((posx > width) || (posx < 0))
//                dx = -dx;
//            if ((posy > height) || (posy < 800))
//                dy = -dy;
//            if((posy) < 800)
//                dy = -dy;

            //&& (posx + b[0].diameter > p.x) && (posx + b[0].diameter < p.x + 300

            if(bump == true){
                p.update();
            }

            if(b[0].y+b[0].diameter >= p.y && b[0].y+b[0].diameter <= p.y+30){
                if(b[0].x + 95 >= p.x  && b[0].x + 100 <= p.x + 500){
                    b[0].dy = -b[0].dy;
                }
            }

//            if(b[0].y > 1250){
//                paused = !paused;
//            }

            //for (int i = 0; i < 5; ++i)
                b[0].update();


        }
        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();

                width = canvas.getWidth();
                height = canvas.getHeight();

                // Draw the background color
                canvas.drawColor(Color.argb(255, 26, 128, 182));

                // Choose the brush color for drawing
                paint.setColor(Color.argb(255, 255, 255, 255));
//                canvas.drawLine(0, 0, 300, y, paint);


                // canvas.drawCircle(posx, posy, 30l, paint);
                //for (int i = 0; i < 5; ++i) {
                    b[0].width = width;
                    b[0].height = height;
                    b[0].draw(canvas, paint);
                //}

                // drawing the paddle
                p.width = width;
                p.height = height;
                p.draw(canvas, paint);


                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

//        @Override
//        public boolean onTouchEvent(MotionEvent motionEvent) {
//            if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN)
//                paused = !paused;
//            return true;
//        }

        @Override
        public boolean onTouchEvent(MotionEvent motionEvent){
            int x = (int)motionEvent.getX();
            if(motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                if(x < 500 && p.dx > 0 || x >= 500 && p.dx < 0){
                    p.dx = -p.dx;
                }
                bump = !bump;
            }
            if(motionEvent.getAction() == android.view.MotionEvent.ACTION_UP)
                bump = !bump;
            return true;
        }

    }


    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the gameView resume method to execute
        asteroidView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the gameView pause method to execute
        asteroidView.pause();
    }

}

//public class MainActivity extends AppCompatActivity{
//
//    Movement movement = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final TextView scoreboard = findViewById(R.id.scoreboard);
//        final Button startButton = findViewById(R.id.startButton);
//        final ImageButton goon = findViewById(R.id.goon);
//        final TextView timer = findViewById(R.id.timer);
//
//        Paint paint = new Paint();
//        movement.setPaint(paint);
//        //customSurfaceView.drawBall();
//        movement.test();
//
////        movement = new Movement(this);
////        setContentView(movement);
////        movement.run();
//    }

//    int score = 0;
//    int highscore = 0;
//    int time = 60;
//    Movement customSurfaceView = null;
//
//    void startTimer() {
//        Executors.newSingleThreadScheduledExecutor().schedule(runnable, 0, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void run(){
//        System.out.println("Why is this called?");
//    }
//
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            time--;
//
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final TextView scoreboard = findViewById(R.id.scoreboard);
//        final Button startButton = findViewById(R.id.startButton);
//        final ImageButton goon = findViewById(R.id.goon);
//        final TextView timer = findViewById(R.id.timer);
//
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                score = 0;
//                scoreboard.setText("Score: " + score +"\nHighscore: " + highscore);
//                time = 60;
//                startTimer();
//                timer.setText("Time Remaining: " + time);
//                customSurfaceView.run();
//            }
//        });
//
//        goon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(time > 0) {
//                    score++;
//                    if (score > highscore) {
//                        highscore = score;
//                    }
//                    scoreboard.setText("Score: " + score + "\nHighscore: " + highscore);
//
//                    double xD = (Math.random() * 950) + 0;
//                    double yD = (Math.random() * 1350) + 100;
//                    float x = (float) xD;
//                    float y = (float) yD;
//
//                    goon.setX(x);
//                    goon.setY(y);
//                }
//            }
//        });
//    }

//}

