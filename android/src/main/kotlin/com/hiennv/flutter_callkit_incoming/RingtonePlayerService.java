package com.hiennv.flutter_callkit_incoming;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import com.hiennv.flutter_callkit_incoming.FlutterCallkitIncomingPlugin;

import android.widget.Toast;

public class RingtonePlayerService extends Service {
    ;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //getting systems default ringtone
        FlutterCallkitIncomingPlugin.Companion.setMyPlayer(MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI));
        //setting loop play to true
        //this will make the ringtone continuously playing
        FlutterCallkitIncomingPlugin.Companion.getMyPlayer().setLooping(true);
        //staring the player
        FlutterCallkitIncomingPlugin.Companion.getMyPlayer().start();
//        Handler h = new Handler();
//        long delayInMilliseconds = 60000;
//        h.postDelayed(new Runnable() {
//            public void run() {
//                FlutterCallkitIncomingPlugin.Companion.getMyPlayer().stop();
//            }
//        }, delayInMilliseconds);
        //we have some options for service
        //start sticky means service will be explicity started and stopped
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        FlutterCallkitIncomingPlugin.Companion.getMyPlayer().stop();
    }
}
