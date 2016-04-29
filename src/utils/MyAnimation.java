package com.example.txj.myresume.myutils;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

/**
 * Created by txj on 2016/4/26.
 */
public class MyAnimation {

    private ImageButton imageButton;
    private int img_id;


    public MyAnimation(Activity activity, int img_id){
        this.img_id = img_id;
        imageButton = (ImageButton) activity.findViewById(img_id);
    }
    /**
     * RotateAnimation
     */
    public void startRotateAnimation() {
        Animation an; //declare a Animation.
        an = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        an.setInterpolator(new LinearInterpolator()); //not be stopped.
        an.setRepeatCount(50); //number of replay.
        an.setFillAfter(false); //stop in the end.
        an.setDuration(4000); //the time it round one time.

        imageButton.startAnimation(an); //start Animation
    }

    /**
     * TranslateAnimation
     * @param translateStep the length of translate.
     */
    public void startMoveAnimation(int translateStep){
        Animation an;
        float startX = imageButton.getX();
        float startY = imageButton.getY();
        an = new TranslateAnimation(startX, startX + translateStep, startY, startY);
        an.setRepeatCount(200);
        an.setDuration(1000);
        imageButton.startAnimation(an);
    }

    /**
     * stop animation.
     */
    public void stopAnimation(){
        imageButton.clearAnimation();
    }

}
