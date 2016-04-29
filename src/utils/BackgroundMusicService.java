package com.example.txj.myresume.myutils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.txj.myresume.R;

public class BackgroundMusicService extends Service {
    private MediaPlayer player;


    public BackgroundMusicService() {}

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * display the background music.
         */

        player = MediaPlayer.create(this, R.raw.backgroundmusic);
        player.start();
    }

    /**
     * pause the music.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.pause();
    }


}
