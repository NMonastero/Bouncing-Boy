package com.example.bouncingboy;

//import android.support.v4.app.AppCompatActivity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageButton;

import static android.graphics.Color.GREEN;

public class Movement extends SurfaceView implements Runnable {

    Thread gameThread = null;
    SurfaceHolder ourHolder;
    volatile boolean playing;
    boolean paused = true;
    Canvas canvas;
    Paint paint;
    int y;
    int posx, posy;
    int dx, dy;
    int height, width;
    boulder[] b;

    private long thisTimeFrame;
    public Movement(Context context) {
        super(context);

        ourHolder = getHolder();
        paint = new Paint();
    }

    @Override
    public void run() {
        Random r = new Random();
        b = new boulder[5];
        posx = 50;
        posy = 50;
        dx = 20;
        dy = 45;
        for (int i = 0; i < 5; ++i) {
            b[i] = new boulder();
            b[i].x = r.nextInt(50);
            b[i].y = r.nextInt(50);
            b[i].dx = r.nextInt(30) - 15;
            b[i].dy = r.nextInt(30) - 15;
            b[i].diameter = 95;
        }


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
        if ((posx > width) || (posx < 0))
            dx = -dx;
        if ((posy > height) || (posy < 0))
            dy = -dy;

        for (int i = 0; i < 5; ++i)
            b[i].move();

    }
    public void draw() {
        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            Paint surfaceBackground = new Paint();

            width = canvas.getWidth();
            height = canvas.getHeight();

            // Draw the background color
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            paint.setColor(GREEN);
            canvas.drawRect(0,0, this.getWidth(), this.getHeight(), surfaceBackground);

            // Choose the brush color for drawing
            paint.setColor(Color.argb(255, 255, 255, 255));
            canvas.drawLine(0, 0, 300, y, paint);


             canvas.drawCircle(posx, posy, 30l, paint);
            for (int i = 0; i < 5; ++i) {
                b[i].width = width;
                b[i].height = height;
                b[i].draw(canvas, paint);
            }

            //canvas.drawCircle(b.x, b.y, 50, paint);

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

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == android.view.MotionEvent.ACTION_DOWN)
            paused = !paused;
        return true;
    }

    public class boulder {
        float x, y, dx, dy, diameter;
        float width, height;

        public void move() {
            x += dx;
            y += dy;
            if (x < 0) dx = -dx;
            if (y < 0) dy = -dy;
            if (x > width) dx = -dx;
            if (y > height) dy = -dy;
        }

        public void draw(Canvas canvas, Paint paint) {
            canvas.drawCircle(x, y, diameter, paint);
        }

    }
}

//    Thread gameThread = null;
//    SurfaceHolder ourHolder;
//    volatile boolean playing;
//    boolean paused = true;
//    Canvas canvas;
//    Paint paint;
//    float y, x;
//    int posx, posy;
//    int dx, dy;
//    int height, width;
//    ImageButton goon;
//
//    public static void main(String args[]){
//
//    }
//
//    public Movement(Context context) {
//        super(context, null);
//
//        ourHolder = getHolder();
//
//        paint = new Paint();
//
//        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
//        paint.setStyle(Paint.Style.FILL);
//    }
//
//    @Override
//    public void run(){
//        goon = findViewById(R.id.goon);
//        posx = 50;
//        posy = 50;
//        dx = 20;
//        dy = 45;
//
//        while(playing) {
//            update();
//            draw();
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//
//            }
//        }
//    }
//
//    public void move(float x, float y, ImageButton goon){
//        if(goon.getX() + 350 >= x + 1000 || goon.getX() - x < 0){
//            x = x * -1;
//        }
//        if(goon.getY() + 250 >= y + 1000 || goon.getY() - y < 0){
//            y = y * -1;
//        }
//
//        goon.setX(goon.getX()+x);
//        goon.setY(goon.getY()+y);
//
//    }
//
//    public void draw() {
//        if (ourHolder.getSurface().isValid()) {
//            // Lock the canvas ready to draw
//            canvas = ourHolder.lockCanvas();
//
//            width = canvas.getWidth();
//            height = canvas.getHeight();
//
//            // Draw the background color
//            //canvas.drawColor(Color.argb(255, 26, 128, 182));
//            Paint surfaceBackground = new Paint();
//            surfaceBackground.setColor(Color.argb(100, 26, 128, 182));
//
//            paint.setColor(Color.argb(255, 255, 0, 0));
//            canvas.drawLine(0, 0, 300, y, paint);
//
//            //for some reason, this does nothing
//            paint.setColor(GREEN);
//            canvas.drawCircle(100, 100, 100, paint);
//
//            ourHolder.unlockCanvasAndPost(canvas);
//        }
//    }
//
//    public void update(){
//        posx += dx;
//        posy += dy;
//        if ((posx > width) || (posx < 0))
//            dx = -dx;
//        if ((posy > height) || (posy < 0))
//            dy = -dy;
//
//        x += dx;
//        y += dy;
//        if (x < 0) dx = -dx;
//        if (y < 0) dy = -dy;
//        if (x > width) dx = -dx;
//        if (y > height) dy = -dy;
//
//        this.move(dx, dy, goon);
//
//    }
//
//    public class goon {
//        float x, y, dx, dy, diameter;
//        float width, height;
//
//        public void update()
//        {
//            x += dx;
//            y += dy;
//            if (x < 0) dx = -dx;
//            if (y < 0) dy = -dy;
//            if (x > width) dx = -dx;
//            if (y > height) dy = -dy;
//        }
//
//        public void draw(Canvas canvas, Paint paint)
//        {
//            canvas.drawCircle(x, y, diameter, paint);
//        }
//
//    }

//}
