package com.example.bouncingboy;

import android.animation.ObjectAnimator;
import android.view.View;
import android.content.Context;
import android.widget.ImageButton;

public class Movement extends View {

    public static void main(String args[]){

    }

    public Movement(Context context) {
        super(context, null);
    }

    public static double move(float x, float y, ImageButton goon){
        if(goon.getX() + 350 >= x + 1000 || goon.getX() - x < 0){
            x = x * -1;
        }
        if(goon.getY() + 250 >= y + 1000 || goon.getY() - y < 0){
            y = y * -1;
        }

        goon.setX(goon.getX()+x);
        goon.setY(goon.getY()+y);

        return 0;
    }
}
