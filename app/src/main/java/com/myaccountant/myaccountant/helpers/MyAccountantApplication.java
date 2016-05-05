package com.myaccountant.myaccountant.helpers;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.firebase.client.Firebase;
import com.firebase.client.PersistentStorage;
import com.myaccountant.myaccountant.ChatBot.BrainLogger;
import com.myaccountant.myaccountant.ChatBot.BrainService;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

/**
 * Created by user on 12/1/2015.
 */
public class MyAccountantApplication extends Application{
    private static BrainService mService;
    private static boolean mBound = false;
    Firebase myFirebaseRef;

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BrainService.LocalBinder binder = (BrainService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "FHb3jTxKjmhRrNypHc2WLoJFxEvNBtAT5e2giabr", "AcFM7Tbjc8MO51DhduHglicIFf2v4GdaoyVVGWDa");

        BrainLogger.setup(this);

        Intent brainIntent = new Intent(this, BrainService.class);
        brainIntent.setAction(BrainService.ACTION_START);
        startService(brainIntent);

        // Bind to LocalService
        bindService(brainIntent, mConnection, Context.BIND_AUTO_CREATE);

        Firebase myFirebaseRef;
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistentStorage(new PersistentStorage() {
            @Override
            public void set(String s, String s1) {

            }

            @Override
            public void get(String s) {

            }

            @Override
            public boolean hasKey(String s) {
                return false;
            }

            @Override
            public void deleteKey(String s) {

            }
        });
        myFirebaseRef = new Firebase("https://myaccountantchat.firebaseio.com");
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public static boolean isBrainLoaded(){
        if (mBound) {
            return mService.isBrainLoaded();
        }
        return false;
    }

    public static void updateParseInstallation(ParseUser user){
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("userId", user.getObjectId());
        installation.saveInBackground();
    }
}
