package com.example.bouncingboy;

import android.graphics.Canvas;
import android.graphics.Paint;

public class boulder {
    float x, y, dx, dy, diameter;
    float width, height;

    public void update()
    {
        x += dx;
        y += dy;
        if (x < 0) dx = -dx;
        if (y < 0) dy = -dy;
        if (x > width) dx = -dx;
        if (y > height) dy = -dy;
    }

    float getWidth(){
        return width;
    }

    float getHeight(){
        return height;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(x, y, diameter, paint);
    }

}
