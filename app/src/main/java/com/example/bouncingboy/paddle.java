package com.example.bouncingboy;

import android.graphics.Canvas;
import android.graphics.Paint;

public class paddle {
    float x, y, dx, dy;
    float width, height;

    public void update()
    {
        x += dx;
        //y += dy;
        if (x < 5) dx = -dx;
        //if (y < 0) dy = -dy;
        if (x + 250 > width) dx = -dx;
       // if (y > height) dy = -dy;
    }

    float getWidth(){
        return width;
    }

    float getHeight(){
        return height;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawRect(x,y,x+250,y+30,paint);
    }
}
