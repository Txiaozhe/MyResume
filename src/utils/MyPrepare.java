package com.example.txj.myresume.myutils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.txj.myresume.R;
import com.example.wigthandheight.WidthAndHeight;

/**
 * Created by txj on 2016/4/27.
 */
public class MyPrepare {
    private Activity activity;
    private int img_show_is_played_id;
    private int top_tab_id;
    private int label_tab_id;
    private int last_img_id;
    private int next_img_id;

    private int me_image_id;
    private int me_img_id;

    /**
     * set my head portrait in first activity(MainActivity)
     * @param activity  context
     * @param me_image_id  id of drawable image.
     * @param me_img_id  id of ImageButton.
     */
    public MyPrepare(Activity activity, int me_image_id, int me_img_id){
        this.activity = activity;
        this.me_image_id = me_image_id;
        this.me_img_id = me_img_id;
    }

    /**
     * set task_tab\label_tab\two button of next and last\imageButton of played
     * @param activity  context
     * @param img_show_is_played_id  id of this ImageButton.
     * @param top_tab_id  id of task tab LinearLayout.
     * @param label_tab_id  id of label tab LinearLayout.
     * @param last_img_id  id of last tab ImageButton.
     * @param next_img_id  id of next tab ImageButton.
     */
    public MyPrepare(Activity activity, int img_show_is_played_id, int top_tab_id,
                     int label_tab_id, int last_img_id, int next_img_id) {
        this.activity = activity;
        this.img_show_is_played_id = img_show_is_played_id;
        this.top_tab_id = top_tab_id;
        this.label_tab_id = label_tab_id;
        this.last_img_id = last_img_id;
        this.next_img_id = next_img_id;
    }

    public void doPrepare() throws Exception {
        /**
         * start the animation of music ImageButton.
         */

        MyAnimation myRotateAnimation;

        myRotateAnimation = new MyAnimation(activity, img_show_is_played_id);
        myRotateAnimation.startRotateAnimation();

        /**
         * make the top_lab and label_lab to transparent.
         */
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        /**
         * set the height of top_tab and label.
         * (LinearLayout id, scale of parentWidth, scale of parentHeight).
         * WidthAndHeight：it is a class in a jar package build by myself.
         */
        WidthAndHeight setTab = new WidthAndHeight(activity);

        /**
         * if the sdk version earlier than 4.4,the top tab is gone.
         */
        if(Integer.parseInt(android.os.Build.VERSION.SDK) <= 19) {
            LinearLayout task_tab = (LinearLayout) activity.findViewById(top_tab_id);
            task_tab.setVisibility(View.GONE);
        }

        setTab.setWidthAndHeight(top_tab_id, 1, 28);
        setTab.setWidthAndHeight(label_tab_id, 1, 12);

        /**
         * start the animation of last and next ImageButton.
         */
        if (last_img_id != 0) {
            MyAnimation myTranslateAnimation_last = new MyAnimation(activity, last_img_id);
            myTranslateAnimation_last.startMoveAnimation(-20);
        }

        if(next_img_id != 0) {
            MyAnimation myTranslateAnimation_next = new MyAnimation(activity, next_img_id);
            myTranslateAnimation_next.startMoveAnimation(20);
        }
    }

    /**
     * set round image.
     */
    public void setRoundImage(){
        ImageView img_me = (ImageView) activity.findViewById(me_img_id);
        Resources res = activity.getResources();
        Bitmap me = BitmapFactory.decodeResource(res, me_image_id);
        img_me.setImageBitmap(toRoundBitmap(me));
    }

    /**
     * cat a bitmap to round.
     *
     * @param bitmap a bitmap will be cut to round.
     * @return
     */
    private Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

}
