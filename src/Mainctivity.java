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

/**
 * Main page of this app "MyResume".
 */
public class MainActivity extends Activity {
    /**
     * declare some variables of widget
     */

    /**
     * declare some variables.
     */
    private Intent i; //intent of BackgroundMusicService.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        /**
         * test: get the sdk version and rom version.
         */
        //Toast.makeText(this, android.os.Build.VERSION.RELEASE + "
        // "+ android.os.Build.VERSION.SDK, Toast.LENGTH_LONG).show();

        /**
         * start to display the background music, by Service:BackgroundMusicService.
         */
        i = new Intent(MainActivity.this, BackgroundMusicService.class);
        startService(i);

        /**
         * start the animation of music ImageButton.
         * make the top_lab and label_lab to transparent.
         * set the height of top_tab and label.
         * (LinearLayout id, scale of parentWidth, scale of parentHeight).
         * WidthAndHeight：it is a class in a jar package build by myself.
         * start the animation of last and next ImageButton.
         */

        MyPrepare prepare = new MyPrepare(this, R.id.main_id_img_show_is_played,
                R.id.main_id_top_tab, R.id.main_id_label, 0,
                R.id.main_id_img_next_page);
        try {
            prepare.doPrepare();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        /**
         * set round image.
         */
        MyPrepare prepare_img = new MyPrepare(this, R.drawable.me, R.id.main_id_img_me);
        prepare_img.setRoundImage();

    }

    /**
     * click action of ImageButton:img_show_is_played
     *
     * @param v
     */
    private Boolean is_played = true; //weather the music is played or not.

    public void stop_Or_start(View v) {
        MyAnimation myRotateAnimation = new MyAnimation(MainActivity.this,
                R.id.main_id_img_show_is_played);

        if (is_played) {
            stopService(i); //stop music.
            myRotateAnimation.stopAnimation(); //stop animation.
            //Toast.makeText(MainActivity.this, "shop music!", Toast.LENGTH_SHORT).show();
        } else {
            startService(i); //play music.
            myRotateAnimation.startRotateAnimation(); //start animation.
            //Toast.makeText(MainActivity.this, "start music!", Toast.LENGTH_SHORT).show();
        }
        is_played = !is_played;
    }

    public void toNextPage(View v) {
        //Toast.makeText(MainActivity.this, "next page", Toast.LENGTH_SHORT).show();
        //go to the SecondActivity.
        Intent i = new Intent(MainActivity.this, SecondActivity.class);
        i.putExtra("is_played", is_played);
        startActivity(i);
    }

}
