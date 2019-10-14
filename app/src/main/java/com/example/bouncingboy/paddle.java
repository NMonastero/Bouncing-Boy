package com.example.bouncingboy;

import android.graphics.Canvas;
import android.graphics.Paint;

public class paddle {
    float padx, pady, paddx, paddy;
    float width, height;

    public void update()
    {
        padx += paddx;
        pady += paddy;
        if (padx < 0) paddx = -paddx;
        if (pady < 0) paddy = -paddy;
        if (padx > width) paddx = -paddx;
        if (pady > height) paddy = -paddy;
    }

    float getWidth(){
        return width;
    }

    float getHeight(){
        return height;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawRect(padx,pady,padx+300,pady+30,paint);
    }
}
