package com.example.txj.myresume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.txj.myresume.myutils.BackgroundMusicService;
import com.example.txj.myresume.myutils.MyAnimation;
import com.example.txj.myresume.myutils.MyPrepare;


public class ThirdActivity extends Activity {

    /**
     * declare some variables.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_third);

        /**
         * start the animation of music ImageButton.
         * make the top_lab and label_lab to transparent.
         * set the height of top_tab and label.
         * (LinearLayout id, scale of parentWidth, scale of parentHeight).
         * WidthAndHeight：it is a class in a jar package build by myself.
         * start the animation of last and next ImageButton.
         */

        MyPrepare prepare = new MyPrepare(this, R.id.third_id_img_show_is_played,
                R.id.third_id_top_tab, R.id.third_id_label, R.id.third_id_img_last_page,
                R.id.third_id_img_next_page);

        try {
            prepare.doPrepare();
        }catch (Exception e){
            Toast.makeText(ThirdActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * click action of ImageButton:img_show_is_played
     */
    private Boolean is_played = true;
    public void stop_Or_start(View v) {

        MyAnimation myRotateAnimation = new MyAnimation(ThirdActivity.this,
                R.id.third_id_img_show_is_played);

        Intent i = new Intent(ThirdActivity.this, BackgroundMusicService.class);
        startService(i);
        if (is_played) {
            stopService(i); //stop music.
            myRotateAnimation.stopAnimation(); //stop animation.
            //Toast.makeText(SecondActivity.this, "shop music!", Toast.LENGTH_SHORT).show();
        } else {
            startService(i); //play music.
            myRotateAnimation.startRotateAnimation(); //start animation.
            //Toast.makeText(SecondActivity.this, "start music!", Toast.LENGTH_SHORT).show();
        }
        is_played = !is_played;
    }

    /**
     * go to the main activity.
     * @param v
     */
    public void toLastPage(View v) {
        //Toast.makeText(SecondActivity.this, "last page", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * go to the Forth activity.
     * @param v
     */
    public void toNextPage(View v) {
        //Toast.makeText(ThirdActivity.this, "next page", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ThirdActivity.this, ForthActivity.class);
        startActivity(i);
    }
}
