package com.example.bouncingboy;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    float y, x;
    int posx, posy;
    int dx, dy;
    int height, width;
    ImageButton goon;

    public static void main(String args[]){

    }

    public Movement(Context context) {
        super(context);

        ourHolder = getHolder();
        paint = new Paint();
    }

    @Override
    public void run(){
        int time = 60;
        goon = findViewById(R.id.goon);
        posx = 50;
        posy = 50;
        dx = 20;
        dy = 45;

        while(playing) {
            if(time != 0) {
                update();
            }
            draw();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

            }
        }
    }

    public void move(float x, float y, ImageButton goon){
        if(goon.getX() + 350 >= x + 1000 || goon.getX() - x < 0){
            x = x * -1;
        }
        if(goon.getY() + 250 >= y + 1000 || goon.getY() - y < 0){
            y = y * -1;
        }

        goon.setX(goon.getX()+x);
        goon.setY(goon.getY()+y);

    }

    public void draw() {
        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            width = canvas.getWidth();
            height = canvas.getHeight();

            // Draw the background color
            //canvas.drawColor(Color.argb(255, 26, 128, 182));
            Paint surfaceBackground = new Paint();
            surfaceBackground.setColor(Color.argb(100, 26, 128, 182));

            paint.setColor(Color.argb(255, 255, 0, 0));
            canvas.drawLine(0, 0, 300, y, paint);

            //for some reason, this does nothing
            paint.setColor(GREEN);
            canvas.drawCircle(100, 100, 100, paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void update(){
        posx += dx;
        posy += dy;
        if ((posx > width) || (posx < 0))
            dx = -dx;
        if ((posy > height) || (posy < 0))
            dy = -dy;

        x += dx;
        y += dy;
        if (x < 0) dx = -dx;
        if (y < 0) dy = -dy;
        if (x > width) dx = -dx;
        if (y > height) dy = -dy;

        this.move(dx, dy, goon);

        //this also does nothing for no reason
        paint.setColor(GREEN);
        canvas.drawCircle(100, 100, 100, paint);

    }

}
