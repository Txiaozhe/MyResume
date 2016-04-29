package com.example.txj.myresume;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.txj.myresume.myutils.BackgroundMusicService;
import com.example.txj.myresume.myutils.MyAnimation;
import com.example.txj.myresume.myutils.MyPrepare;

public class ForthActivity extends Activity {

    /**
     * declare some variables.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_forth);

        /**
         * start the animation of music ImageButton.
         * make the top_lab and label_lab to transparent.
         * set the height of top_tab and label.
         * (LinearLayout id, scale of parentWidth, scale of parentHeight).
         * WidthAndHeight：it is a class in a jar package build by myself.
         * start the animation of last and next ImageButton.
         */

        MyPrepare prepare = new MyPrepare(this, R.id.forth_id_img_show_is_played,
                R.id.forth_id_top_tab, R.id.forth_id_label, R.id.forth_id_img_last_page,
                0);

        try {
            prepare.doPrepare();
        }catch (Exception e){
            Toast.makeText(ForthActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * click action of ImageButton:img_show_is_played
     */
    private Boolean is_played = true;

    public void stop_Or_start(View v) {

        MyAnimation myRotateAnimation = new MyAnimation(ForthActivity.this,
                R.id.forth_id_img_show_is_played);

        Intent i = new Intent(ForthActivity.this, BackgroundMusicService.class);
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
     *
     * @param v
     */
    public void toNextPage(View v) {
        Toast.makeText(ForthActivity.this, "next page", Toast.LENGTH_SHORT).show();
    }

    /**
     * ImageButton of phone me.
     * @param v
     */
    public void phoneHim(View v) {
        Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:15733206382"));
        startActivity(intent);
    }
}
