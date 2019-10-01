package com.example.bouncingboy;

import android.animation.ObjectAnimator;
import android.view.View;
import android.content.Context;
import android.widget.ImageButton;

public class Movement extends View {

    public Movement(Context context) {
        super(context, null);
    }

    public void startMove(ImageButton b) {
        double xD = (Math.random()*2)+0.1;
        double yD = (Math.random()*2)+0.1;
        float x = (float) xD;
        float y = (float) yD;
        int time = 0;

        while(b.getX() <= x + 1000 && b.getX() - x >= 0 && b.getY() <= y + 2000 && b.getY() - y >= 0){
            b.setX(b.getX()+x);
            b.setY(b.getY()+y);
            ObjectAnimator animation = ObjectAnimator.ofFloat(b, "translationX", 100f);
            animation.setDuration(1000);
            animation.start();
            if(time == 3000){
                break;
            }
            time++;
        }
    }
}
