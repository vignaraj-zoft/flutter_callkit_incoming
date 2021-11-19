package com.hiennv.flutter_callkit_incoming;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;

import android.widget.Toast;

public class RingtonePlayerService extends Service {
    MediaPlayer myPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone
        myPlayer = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);
        //setting loop play to true
        //this will make the ringtone continuously playing
        myPlayer.setLooping(true);
        //staring the player
        myPlayer.start();
        Handler h = new Handler();
        long delayInMilliseconds = 60000;
        h.postDelayed(new Runnable() {
            public void run() {
                myPlayer.stop();
            }
        }, delayInMilliseconds);
        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        myPlayer.stop();
    }
}
